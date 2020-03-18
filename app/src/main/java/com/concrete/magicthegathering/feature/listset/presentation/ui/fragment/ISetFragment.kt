package com.concrete.magicthegathering.feature.listset.presentation.ui.fragment

import com.concrete.magicthegathering.data.model.domain.SetDomain

interface ISetFragment {
    fun showSuccess(setDomain: SetDomain)
    fun showLoading()
    fun showError()
}