package com.concrete.magicthegathering.feature.listset.presentation.ui.fragment

import com.concrete.magicthegathering.data.model.domain.ListSetDomain

interface ISetFragment {
    fun showSuccess(listSetDomain: List<ListSetDomain>)
    fun showLoading()
    fun showError()
}