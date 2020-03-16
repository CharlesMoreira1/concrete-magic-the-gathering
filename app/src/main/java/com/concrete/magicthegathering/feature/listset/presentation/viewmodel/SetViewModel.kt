package com.concrete.magicthegathering.feature.listset.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.concrete.magicthegathering.core.base.BaseViewModel
import com.concrete.magicthegathering.core.helper.Resource
import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.cards.CardsResponse
import com.concrete.magicthegathering.data.model.entity.sets.Set
import com.concrete.magicthegathering.feature.listset.repository.SetRepository
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.math.ceil

class SetViewModel(private val repository: SetRepository) : BaseViewModel() {
    private val mutableLiveDataListSets = MutableLiveData<Resource<List<Set>>>()
    private val mutableLiveDataListCards = MutableLiveData<Resource<List<Card>>>()

    private var listCards = arrayListOf<Card>()
    private var lastPage = 0
    var releasedLoad: Boolean = true

    init {
        fetchListSets()
    }

    val getLiveDataListSets: LiveData<Resource<List<Set>>>
        get() = mutableLiveDataListSets

    val getLiveDataListCards: LiveData<Resource<List<Card>>>
        get() = mutableLiveDataListCards

    private fun fetchListSets() {
        mutableLiveDataListSets.loading()

        viewModelScope.launchWithCallback(
            onSuccess = {
                mutableLiveDataListSets.success(repository.getListSets())
            },
            onError = {
                mutableLiveDataListSets.error(it)
            })
    }

    fun fetchListCards(codeID: String) {
        viewModelScope.launch {
            try {
                setupFirstPage(codeID)

                if (lastPage > 1) {
                    multipleRequestsListCards(codeID)
                } else {
                    mutableLiveDataListCards.success(listCards)
                    releasedLoad = true
                }
            } catch (t: Throwable) {
                mutableLiveDataListCards.error(t)
            }
        }
    }

    private suspend fun setupFirstPage(codeID: String) {
        lastPage = 0
        withContext(Dispatchers.IO) {
            with(repository.getResponseCards(codeID)) {
                lastPage = roundTotalPage()

                listCards.clear()
                listCards.addAll(this.body()!!.cards)
            }
        }
    }

    private suspend fun multipleRequestsListCards(codeID: String) {
        withContext(Dispatchers.IO) {
            (2..lastPage).map {
                delay(1000)
                async { listCards.addAll(repository.getListCards(codeID, it)!!) }
            }.awaitAll()

            mutableLiveDataListCards.success(listCards)
        }
    }

    fun refreshListSets(){
        fetchListSets()
        releasedLoad = true
        listCards.clear()
        lastPage = 0
    }

    private fun Response<CardsResponse>.roundTotalPage(): Int {
        val valueDouble = (headers()["total-count"]!!.toInt() / headers()["page-size"]!!.toDouble())
        return ceil(valueDouble).toInt()
    }
}