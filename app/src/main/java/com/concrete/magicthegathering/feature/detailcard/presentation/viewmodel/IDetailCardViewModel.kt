package com.concrete.magicthegathering.feature.detailcard.presentation.viewmodel

import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite

interface IDetailCardViewModel {
    fun findByCard(idCard: String)
    fun insertOrRemoveCard(insertEnabled: Boolean, cardFavorite: CardFavorite)
}