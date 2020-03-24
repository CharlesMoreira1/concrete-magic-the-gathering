package com.concrete.magicthegathering.data.model.mapper

import com.concrete.magicthegathering.data.model.domain.CardDomain
import com.concrete.magicthegathering.data.model.domain.ListSetDomain
import com.concrete.magicthegathering.data.model.domain.SetDomain
import com.concrete.magicthegathering.data.model.domain.TypeSetDomain
import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.sets.Set

object SetMapper {

    private const val BASE_URL_IMAGE_CARD = "https://gatherer.wizards.com/Handlers/Image.ashx?name=%s&type=card"

    suspend fun transformEntityToDomain(set: Set, listCard: suspend (String) -> List<CardDomain>): List<ListSetDomain> {
        val setDomain = set.let {
            SetDomain(nameSet = it.name, listCardDomain = listCard(it.code))
        }

        return setDomain.transformToListSet()
    }

    private fun SetDomain.transformToListSet(): List<ListSetDomain> {
        val listSetDomain = arrayListOf<ListSetDomain>()

        listSetDomain.add(this)

        this.listCardDomain.groupBy { it.typeName }.map {
            listSetDomain.add(TypeSetDomain(it.key))
            listSetDomain.addAll(it.value)
        }

        return listSetDomain
    }

    fun transformEntityToDomainListCards(listCards: List<Card>): List<CardDomain> {
        val listCardsDomain = arrayListOf<CardDomain>()

        listCards.forEach {card ->
            card.types.map {
                val cardDomain = CardDomain(image = String.format(BASE_URL_IMAGE_CARD, card.name), name = card.name, multiverseid = card.multiverseid.toLong(), typeName = it)
                listCardsDomain.add(cardDomain)
            }
        }

        return listCardsDomain
    }
}