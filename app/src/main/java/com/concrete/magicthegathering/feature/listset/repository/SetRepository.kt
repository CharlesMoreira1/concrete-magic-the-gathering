package com.concrete.magicthegathering.feature.listset.repository

import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.cards.CardsResponse
import com.concrete.magicthegathering.data.model.entity.sets.Set
import com.concrete.magicthegathering.data.source.remote.api.ApiService
import retrofit2.Response

class SetRepository(private val apiService: ApiService) {
    suspend fun getListSets(): List<Set> {
        return apiService.getSetsResponse().sets
    }

    suspend fun getResponseCards(code: String): Response<CardsResponse> {
        return apiService.getCardResponse(code, 1)
    }

    suspend fun getListCards(code: String, page: Int): List<Card>? {
        return apiService.getCardResponse(code, page).body()?.cards
    }
}