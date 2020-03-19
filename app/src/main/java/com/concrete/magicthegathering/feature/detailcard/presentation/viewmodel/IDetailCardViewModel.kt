package com.concrete.magicthegathering.feature.detailcard.presentation.viewmodel

import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite

interface IDetailCardViewModel {
    fun findByCard(multiverseid: Long)
    fun insertOrRemoveCard(insertEnabled: Boolean, cardFavorite: CardFavorite)
}