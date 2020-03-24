package com.concrete.magicthegathering.feature.listset.presentation.ui.fragment

import com.concrete.magicthegathering.data.model.domain.ItemAdapter

interface ISetFragment {
    fun showSuccess(listItemAdapter: List<ItemAdapter>)
    fun showLoading()
    fun showError()
}