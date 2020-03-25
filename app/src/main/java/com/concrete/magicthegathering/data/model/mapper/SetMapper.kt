package com.concrete.magicthegathering.data.model.mapper

import com.concrete.magicthegathering.data.model.domain.CardDomain
import com.concrete.magicthegathering.data.model.ItemType
import com.concrete.magicthegathering.data.model.domain.SetDomain
import com.concrete.magicthegathering.data.model.domain.TypeSetDomain
import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.sets.Set

object SetMapper {

    private const val BASE_URL_IMAGE_CARD = "https://gatherer.wizards.com/Handlers/Image.ashx?name=%s&type=card"

    suspend fun transformEntityToDomain(set: Set, listCard: suspend (String) -> List<CardDomain>): List<ItemType> {
        val setDomain = set.let {
            SetDomain(nameSet = it.name, listCardDomain = listCard(it.code))
        }

        return setDomain.transformToListItemType()
    }

    private fun SetDomain.transformToListItemType(): List<ItemType> {
        val listItemType = arrayListOf<ItemType>()

        if (this.isValid()) {
            listItemType.add(this)
        }

        this.listCardDomain.groupBy { it.typeName }.map {
            val typeSetDomain = TypeSetDomain(it.key)

            if (typeSetDomain.isValid()) {
                listItemType.add(TypeSetDomain(it.key))
                listItemType.addAll(it.value)
            }
        }

        return listItemType
    }

    fun transformEntityToDomainListCards(listCards: List<Card>): List<CardDomain> {
        val listCardsDomain = arrayListOf<CardDomain>()

        listCards.forEach {card ->
            card.types.map {
                val cardDomain = CardDomain(image = String.format(BASE_URL_IMAGE_CARD, card.name), name = card.name, idCard = card.id, typeName = it)

                if (cardDomain.isValid()) {
                    listCardsDomain.add(cardDomain)
                }
            }
        }

        return listCardsDomain
    }
}