package com.concrete.magicthegathering.feature.listset.repository

import com.concrete.magicthegathering.data.model.domain.CardDomain
import com.concrete.magicthegathering.data.model.domain.ListSetDomain
import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.sets.Set
import com.concrete.magicthegathering.data.model.mapper.SetMapper
import com.concrete.magicthegathering.data.source.remote.api.ApiService
import com.concrete.magicthegathering.feature.listset.helper.roundTotalPage
import kotlinx.coroutines.*

class SetRepository(private val apiService: ApiService): ISetRepository {
    private var listCards = arrayListOf<Card>()
    private var listSets = listOf<Set>()
    private var lastPage = 0

    override suspend fun getListSetDomain(position: Int, isFirstRequest: Boolean): List<ListSetDomain>{
        if (isFirstRequest) {
            listSets = apiService.getSetsResponse().sets.sortedByDescending { it.releaseDate }
        }
        val set = listSets[position]

        return SetMapper.transformEntityToDomain(set) { getListCardsDomain(it) }
    }

    private suspend fun getListCardsDomain(codeID: String): List<CardDomain> {
        initFirstPage(codeID)

        if (lastPage > 1) {
            withContext(Dispatchers.IO) {
                (2..lastPage).map { page ->
                    delay(1000)
                    async {
                        listCards.addAll(apiService.getCardResponse(codeID, page).body()?.cards!!)
                    }
                }.awaitAll()
            }
            return SetMapper.transformEntityToDomainListCards(listCards)
        }

        return SetMapper.transformEntityToDomainListCards(listCards)
    }

    private suspend fun initFirstPage(codeID: String) {
        lastPage = 0
        withContext(Dispatchers.IO) {
            with(apiService.getCardResponse(codeID, 1)) {
                lastPage = roundTotalPage()

                listCards.clear()
                listCards.addAll(this.body()!!.cards)
            }
        }
    }
}