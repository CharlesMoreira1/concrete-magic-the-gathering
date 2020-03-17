package com.concrete.magicthegathering.data.model.mapper

import com.concrete.magicthegathering.data.model.domain.CardDomain
import com.concrete.magicthegathering.data.model.domain.SetDomain
import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.sets.Set

object SetMapper {

    suspend fun transformEntityToDomain(set: Set, listCard: suspend (String) -> List<CardDomain>): SetDomain? {
        set.let {
            return SetDomain(nameSet = it.name, listCardDomain = listCard(it.code))
        }
    }

    fun transformEntityToDomainListCards(listCards: List<Card>): List<CardDomain> {
        val listCardsDomain = ArrayList<CardDomain>()

        listCards.forEach {
            val cardsDomain = CardDomain(image = it.imageUrl)
            listCardsDomain.add(cardsDomain)
        }

        return listCardsDomain
    }
}