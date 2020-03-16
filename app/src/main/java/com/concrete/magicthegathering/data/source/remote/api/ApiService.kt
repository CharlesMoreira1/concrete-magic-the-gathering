package com.concrete.magicthegathering.data.source.remote.api

import com.concrete.magicthegathering.data.model.entity.cards.CardsResponse
import com.concrete.magicthegathering.data.model.entity.sets.SetsReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("cards")
    suspend fun getCardResponse(@Query("set") set: String, @Query("page") page: Int): Response<CardsResponse>

    @GET("sets")
    suspend fun getSetsResponse(): SetsReponse
}