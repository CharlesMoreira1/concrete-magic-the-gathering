package com.concrete.magicthegathering.feature.listset.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.concrete.magicthegathering.R
import com.concrete.magicthegathering.core.helper.addPaginationScroll
import com.concrete.magicthegathering.core.helper.observeResource
import com.concrete.magicthegathering.core.util.rotationAnimation
import com.concrete.magicthegathering.data.model.domain.SetDomain
import com.concrete.magicthegathering.feature.listset.presentation.ui.adapter.SetAdapter
import com.concrete.magicthegathering.feature.listset.presentation.viewmodel.SetViewModel
import kotlinx.android.synthetic.main.fragment_set.*
import kotlinx.android.synthetic.main.layout_error_center.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SetFragment : Fragment(R.layout.fragment_set) {

    private lateinit var setAdapter: SetAdapter

    private val viewModel by viewModel<SetViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        setupUI()
    }

    private fun loadData() {
        viewModel.getLiveDataListSets.observeResource(viewLifecycleOwner,
            onSuccess = {
                showSuccess(it)
            },
            onError = {
                showError()
            },
            onLoading = {
                showLoading()
            })
    }

    private fun setupUI() {
        setAdapter = SetAdapter()

        with(recycler_set) {
            adapter = setAdapter
            val gridLayoutManager = GridLayoutManager(context,3)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (setAdapter.getItemViewType(position)) {
                        SetAdapter.ITEM_HEADER -> gridLayoutManager.spanCount
                        SetAdapter.ITEM_LIST_CARDS -> 1
                        else -> gridLayoutManager.spanCount
                    }
                }
            }

            addPaginationScroll(gridLayoutManager,
                loadMoreItems = {
                    viewModel.nextSet()
                    include_loading_bottom.visibility = View.VISIBLE
                },
                isLoading = {
                    viewModel.releasedLoad
                },
                hideOthersItems = {
                    include_loading_center.visibility = View.GONE
                })

            layoutManager = gridLayoutManager
        }
    }

    private fun showSuccess(setDomain: SetDomain){
        setAdapter.addList(setDomain)

        recycler_set.visibility = View.VISIBLE
        include_layout_header.visibility = View.VISIBLE
        include_loading_center.visibility = View.GONE
        include_loading_bottom.visibility = View.GONE
        include_error_center.visibility = View.GONE
        viewModel.releasedLoad = true
    }

    private fun showLoading(){
        include_loading_center.visibility = View.VISIBLE
        include_error_center.visibility = View.GONE
        include_layout_header.visibility = View.GONE
    }

    private fun showError(){
        if (viewModel.countPositionSets > 1){
            include_error_center.visibility = View.GONE
        } else {
            include_error_center.visibility = View.VISIBLE
            include_layout_header.visibility = View.GONE
            recycler_set.visibility = View.GONE

            clickRefresh()
        }

        include_loading_center.visibility = View.GONE
        include_loading_bottom.visibility = View.GONE
    }

    private fun clickRefresh(){
        image_refresh_error.setOnClickListener { view ->
            view.rotationAnimation()

            setAdapter.clearList()
            viewModel.refreshListSets()
        }
    }
}
