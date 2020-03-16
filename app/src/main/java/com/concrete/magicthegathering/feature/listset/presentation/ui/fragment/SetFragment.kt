package com.concrete.magicthegathering.feature.listset.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.concrete.magicthegathering.R
import com.concrete.magicthegathering.core.helper.addPaginationScroll
import com.concrete.magicthegathering.core.helper.observeResource
import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.sets.Set
import com.concrete.magicthegathering.feature.listset.presentation.ui.adapter.SetAdapter
import com.concrete.magicthegathering.feature.listset.presentation.viewmodel.SetViewModel
import kotlinx.android.synthetic.main.fragment_set.*
import kotlinx.android.synthetic.main.layout_error_center.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SetFragment : Fragment() {

    private val setAdapter by lazy {
        SetAdapter()
    }

    private val viewModel by viewModel<SetViewModel>()

    private var listSets = listOf<Set>()
    private var countPositionSets = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_set, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        setupUI()
    }

    private fun loadData() {
        viewModel.getLiveDataListSets.observeResource(viewLifecycleOwner,
            onSuccess = {
                showSuccessSets(it)
            },
            onError = {
                showErrorAll()
            },
            onLoading = {
                showLoadingAll()
            })

        viewModel.getLiveDataListCards.observeResource(viewLifecycleOwner,
            onSuccess = {
                showSuccessCards(it)
            },
            onError = {
                showErrorAll()
            })
    }

    private fun setupUI() {
        with(recycler_set) {
            adapter = setAdapter
            val gridLayoutManager = GridLayoutManager(context, 3)
            layoutManager = gridLayoutManager

            addPaginationScroll(gridLayoutManager,
                loadMoreItems = {
                    nextSet()
                },
                isLoading = {
                    viewModel.releasedLoad
                })

            layoutManager = gridLayoutManager
        }
    }

    private fun showSuccessSets(listSets: List<Set>){
        this.listSets = listSets
        viewModel.fetchListCards(this.listSets[0].code)
        include_error_center.visibility = View.GONE
    }

    private fun showSuccessCards(listCards: List<Card>){
        setAdapter.addList(listCards)
        recycler_set.visibility = View.VISIBLE
        include_layout_header.visibility = View.VISIBLE
        include_loading_center.visibility = View.GONE
        include_loading_bottom.visibility = View.GONE
        include_error_center.visibility = View.GONE
        viewModel.releasedLoad = true
    }

    private fun showLoadingAll(){
        include_loading_center.visibility = View.VISIBLE
        include_error_center.visibility = View.GONE
        include_layout_header.visibility = View.GONE
    }

    private fun showErrorAll(){
        include_error_center.visibility = View.VISIBLE
        include_loading_center.visibility = View.GONE
        include_loading_bottom.visibility = View.GONE
        recycler_set.visibility = View.GONE
        include_layout_header.visibility = View.GONE

        clickRefresh()
    }

    private fun clickRefresh(){
        image_refresh_error.setOnClickListener {
            countPositionSets = 1
            setAdapter.clearList()
            viewModel.refreshListSets()
        }
    }

    private fun nextSet(){
        viewModel.fetchListCards(listSets[countPositionSets++].code)
        include_loading_bottom.visibility = View.VISIBLE
        viewModel.releasedLoad = false
    }
}
