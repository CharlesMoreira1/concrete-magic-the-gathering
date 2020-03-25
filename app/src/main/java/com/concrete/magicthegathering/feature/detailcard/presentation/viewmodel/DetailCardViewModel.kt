package com.concrete.magicthegathering.feature.detailcard.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.concrete.magicthegathering.core.base.BaseViewModel
import com.concrete.magicthegathering.data.model.entity.favorite.CardFavorite
import com.concrete.magicthegathering.feature.detailcard.repository.IDetailCardRepository
import kotlinx.coroutines.launch

class DetailCardViewModel(private val repository: IDetailCardRepository) : BaseViewModel(),
    IDetailCardViewModel {
    lateinit var getLiveDataCard: LiveData<CardFavorite?>

    override fun findByCard(idCard: String) {
        getLiveDataCard = repository.findByCard(idCard)
    }

    override fun insertOrRemoveCard(insertEnabled: Boolean, cardFavorite: CardFavorite) {
        viewModelScope.launch {
            if (insertEnabled) {
                repository.removeCard(cardFavorite)
            } else {
                repository.insertCard(cardFavorite)
            }
        }
    }
}