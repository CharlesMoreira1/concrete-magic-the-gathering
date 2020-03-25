package com.concrete.magicthegathering.feature.detailcard.repository

import androidx.lifecycle.LiveData
import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite
import com.concrete.magicthegathering.data.source.local.dao.FavoriteDao

class DetailCardRepository(private val favoriteDao: FavoriteDao): IDetailCardRepository{

    override fun findByCard(idCard: String): LiveData<CardFavorite?> {
        return favoriteDao.findByCard(idCard)
    }

    override suspend fun insertCard(cardFavorite: CardFavorite){
        favoriteDao.insertCard(cardFavorite)
    }

    override suspend fun removeCard(cardFavorite: CardFavorite){
        favoriteDao.removeCard(cardFavorite.idCard)
    }
}