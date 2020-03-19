package com.concrete.magicthegathering.feature.detailcard.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.concrete.magicthegathering.core.base.BaseViewModel
import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite
import com.concrete.magicthegathering.feature.detailcard.repository.IDetailCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailCardViewModel(private val repository: IDetailCardRepository) : BaseViewModel(), IDetailCardViewModel {
    var getLiveDataCard: LiveData<CardFavorite?>? = null

    override fun findByCard(multiverseid: Long) {
        getLiveDataCard = repository.findByCard(multiverseid)
    }

    override fun insertOrRemoveCard(insertEnabled: Boolean, cardFavorite: CardFavorite) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (insertEnabled) {
                    repository.removeCard(cardFavorite)
                } else {
                    repository.insertCard(cardFavorite)
                }
            }
        }
    }
}