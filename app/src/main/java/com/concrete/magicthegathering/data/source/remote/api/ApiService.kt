package com.concrete.magicthegathering.data.source.remote.api

import com.concrete.magicthegathering.data.model.entity.cards.CardResponse
import com.concrete.magicthegathering.data.model.entity.sets.SetReponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("cards")
    suspend fun getCardResponse(@Query("set") set: String, @Query("page") page: Int,
                                @Query("orderBy") orderBy: String = "types"): Response<CardResponse>

    @GET("sets")
    suspend fun getSetsResponse(): SetReponse
}