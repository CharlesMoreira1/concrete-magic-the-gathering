package com.concrete.magicthegathering.feature.listset.presentation.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.concrete.magicthegathering.data.model.domain.ListSetDomain
import com.concrete.magicthegathering.R
import com.concrete.magicthegathering.core.helper.addPaginationScroll
import com.concrete.magicthegathering.core.helper.observeResource
import com.concrete.magicthegathering.core.util.navigateWithAnimations
import com.concrete.magicthegathering.core.util.rotationAnimation
import com.concrete.magicthegathering.data.model.domain.CardDomain
import com.concrete.magicthegathering.feature.listset.presentation.ui.adapter.SetAdapter
import com.concrete.magicthegathering.feature.listset.presentation.viewmodel.SetViewModel
import kotlinx.android.synthetic.main.fragment_set.*
import kotlinx.android.synthetic.main.layout_error_center.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SetFragment : Fragment(R.layout.fragment_set), ISetFragment {

    private val setAdapter: SetAdapter by lazy {
        SetAdapter {
            navDetailCardFragment(it)
        }
    }

    private val viewModel by viewModel<SetViewModel>()
    private var enableAddListSets = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        setupUI()
        swipeRefresh()
    }

    override fun onResume() {
        super.onResume()
        enableAddListSets = true
    }

    override fun onPause() {
        super.onPause()
        enableAddListSets = false
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
        with(recycler_set) {
            adapter = setAdapter
            val gridLayoutManager = GridLayoutManager(context,3)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (setAdapter.getItemViewType(position)) {
                        ListSetDomain.ITEM_CARDS -> 1
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

    private fun navDetailCardFragment(cardDomain: CardDomain){
        if (viewModel.releasedLoad) {
            val navDirections = SetFragmentDirections.actionSetFragmentToDetailCardFragment(
                cardDomain.image, cardDomain.multiverseid, cardDomain.name)
            findNavController().navigateWithAnimations(navDirections)
        } else {
            Toast.makeText(context, R.string.message_alert_loading_set, Toast.LENGTH_LONG).show()
        }
    }

    override fun showSuccess(listSetDomain: List<ListSetDomain>){
        if (enableAddListSets) {
            setAdapter.addList(listSetDomain)

            recycler_set.visibility = View.VISIBLE
            include_loading_center.visibility = View.GONE
            include_loading_bottom.visibility = View.GONE
            include_error_center.visibility = View.GONE
            viewModel.enablePagination()
        }
    }

    override fun showLoading(){
        include_loading_center.visibility = View.VISIBLE
        include_error_center.visibility = View.GONE
    }

    override fun showError(){
        if (viewModel.countPositionSets > 1){
            include_error_center.visibility = View.GONE
        } else {
            include_error_center.visibility = View.VISIBLE
            recycler_set.visibility = View.GONE

            clickRefresh()
        }

        include_loading_center.visibility = View.GONE
        include_loading_bottom.visibility = View.GONE
    }

    private fun swipeRefresh() {
        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({
                refresh()
                swipe_refresh.isRefreshing = false
            }, 1000)
        }
    }

    private fun clickRefresh(){
        image_refresh_error.setOnClickListener { view ->
            view.rotationAnimation()
            refresh()
        }
    }

    private fun refresh() {
        setAdapter.clearList()
        viewModel.refreshListSets()
    }
}
