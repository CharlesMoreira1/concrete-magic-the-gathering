package com.concrete.magicthegathering.feature.detailcard.repository

import androidx.lifecycle.LiveData
import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite

interface IDetailCardRepository{
    fun findByCard(idCard: String): LiveData<CardFavorite?>
    suspend fun insertCard(cardFavorite: CardFavorite)
    suspend fun removeCard(cardFavorite: CardFavorite)
}