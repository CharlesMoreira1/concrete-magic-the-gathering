package com.concrete.magicthegathering.data.model.mapper

import com.concrete.magicthegathering.data.model.domain.CardDomain
import com.concrete.magicthegathering.data.model.domain.ListSetDomain
import com.concrete.magicthegathering.data.model.domain.SetDomain
import com.concrete.magicthegathering.data.model.domain.TypeSetDomain
import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.sets.Set

object SetMapper {

    suspend fun transformEntityToDomain(set: Set, listCard: suspend (String) -> List<CardDomain>): List<ListSetDomain> {
        val setDomain = set.let {
            SetDomain(nameSet = it.name, listCardDomain = listCard(it.code))
        }

        return setDomain.transformToListSet()
    }

    private fun SetDomain.transformToListSet(): List<ListSetDomain> {
        val listSetDomain = arrayListOf<ListSetDomain>()

        listSetDomain.add(this)

        this.listCardDomain.sortedBy { it.typeName }.groupBy { it.typeName }.map {
            listSetDomain.add(TypeSetDomain(it.key))
            listSetDomain.addAll(it.value)
        }

        return listSetDomain
    }

    fun transformEntityToDomainListCards(listCards: List<Card>): List<CardDomain> {
        val listCardsDomain = ArrayList<CardDomain>()

        listCards.forEach {
            val cardsDomain = CardDomain(image = it.imageUrl, name = it.name, multiverseid = it.multiverseid.toLong(), typeName = it.types[0])
            listCardsDomain.add(cardsDomain)
        }

        return listCardsDomain
    }
}