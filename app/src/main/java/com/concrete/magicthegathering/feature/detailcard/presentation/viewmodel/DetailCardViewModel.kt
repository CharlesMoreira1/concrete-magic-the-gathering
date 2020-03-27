package com.concrete.magicthegathering.feature.detailcard.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.concrete.magicthegathering.core.base.BaseViewModel
import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite
import com.concrete.magicthegathering.feature.detailcard.repository.IDetailCardRepository
import kotlinx.coroutines.launch

class DetailCardViewModel(private val repository: IDetailCardRepository) : BaseViewModel() {
    lateinit var getLiveDataCard: LiveData<CardFavorite?>

    fun findByCard(idCard: String) {
        getLiveDataCard = repository.findByCard(idCard)
    }

    fun insertOrRemoveCard(insertEnabled: Boolean, cardFavorite: CardFavorite) {
        viewModelScope.launch {
            if (insertEnabled) {
                repository.removeCard(cardFavorite)
            } else {
                repository.insertCard(cardFavorite)
            }
        }
    }
}