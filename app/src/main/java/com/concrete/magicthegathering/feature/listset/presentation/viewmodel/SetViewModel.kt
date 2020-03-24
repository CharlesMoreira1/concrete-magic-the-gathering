package com.concrete.magicthegathering.feature.listset.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.concrete.magicthegathering.core.base.BaseViewModel
import com.concrete.magicthegathering.core.helper.Resource
import com.concrete.magicthegathering.data.model.domain.ItemAdapter
import com.concrete.magicthegathering.feature.listset.repository.ISetRepository

class SetViewModel(private val repository: ISetRepository) : BaseViewModel(), ISetViewModel {
    private val mutableLiveDataListSets = MutableLiveData<Resource<List<ItemAdapter>>>()

    var countPositionSets = 1
    var releasedLoad: Boolean = true

    init {
        fetchListSets()
    }

    val getLiveDataListSets: LiveData<Resource<List<ItemAdapter>>>
        get() = mutableLiveDataListSets

    override fun fetchListSets(position: Int, isFirstRequest: Boolean) {
        mutableLiveDataListSets.loading()

        viewModelScope.launchWithCallback(
            onSuccess = {
                mutableLiveDataListSets.success(repository.getListSetDomain(position, isFirstRequest))
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

    override fun enablePagination(){
        releasedLoad = true
    }
}