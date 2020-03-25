package com.concrete.magicthegathering.feature.listset.presentation.ui.fragment

import com.concrete.magicthegathering.data.model.ItemType

interface ISetFragment {
    fun showSuccess(listItemType: List<ItemType>)
    fun showLoading()
    fun showError()
}