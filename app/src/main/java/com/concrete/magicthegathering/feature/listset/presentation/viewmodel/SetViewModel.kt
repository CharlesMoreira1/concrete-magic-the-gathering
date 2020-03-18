package com.concrete.magicthegathering.feature.listset.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.concrete.magicthegathering.core.base.BaseViewModel
import com.concrete.magicthegathering.core.helper.Resource
import com.concrete.magicthegathering.data.model.domain.SetDomain
import com.concrete.magicthegathering.feature.listset.repository.ISetRepository

class SetViewModel(private val repository: ISetRepository) : BaseViewModel(), ISetViewModel {
    private val mutableLiveDataListSets = MutableLiveData<Resource<SetDomain>>()

    var countPositionSets = 1
    var releasedLoad: Boolean = true

    init {
        fetchListSets()
    }

    val getLiveDataListSets: LiveData<Resource<SetDomain>>
        get() = mutableLiveDataListSets

    override fun fetchListSets(position: Int, isFirstRequest: Boolean) {
        mutableLiveDataListSets.loading()

        viewModelScope.launchWithCallback(
            onSuccess = {
                mutableLiveDataListSets.success(repository.getSetDomain(position, isFirstRequest))
            },
            onError = {
                mutableLiveDataListSets.error(it)
            })
    }

    override fun nextSet() {
        fetchListSets(countPositionSets++, false)
        releasedLoad = false
    }

    override fun refreshListSets() {
        countPositionSets = 1
        releasedLoad = false
        fetchListSets()
    }
}