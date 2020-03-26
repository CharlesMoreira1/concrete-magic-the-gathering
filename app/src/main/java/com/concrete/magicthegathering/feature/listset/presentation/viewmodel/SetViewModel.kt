package com.concrete.magicthegathering.feature.listset.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.concrete.magicthegathering.core.base.BaseViewModel
import com.concrete.magicthegathering.core.helper.Resource
import com.concrete.magicthegathering.data.model.ItemType
import com.concrete.magicthegathering.feature.listset.repository.ISetRepository

class SetViewModel(private val repository: ISetRepository) : BaseViewModel() {
    private val mutableLiveDataListSets = MutableLiveData<Resource<List<ItemType>>>()

    var countPositionSets = 1
    var releasedLoad: Boolean = true

    init {
        fetchListSets()
    }

    val getLiveDataListSets: LiveData<Resource<List<ItemType>>>
        get() = mutableLiveDataListSets

    fun fetchListSets(position: Int = 0, isFirstRequest: Boolean = true) {
        mutableLiveDataListSets.loading()

        viewModelScope.launchWithCallback(
            onSuccess = {
                mutableLiveDataListSets.success(repository.getListItemType(position, isFirstRequest))
            },
            onError = {
                mutableLiveDataListSets.error(it)
            })
    }

    fun nextSet() {
        fetchListSets(countPositionSets++, false)
        releasedLoad = false
    }

    fun refreshListSets() {
        countPositionSets = 1
        releasedLoad = false
        fetchListSets()
    }

    fun enablePagination(){
        releasedLoad = true
    }
}