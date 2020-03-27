package com.concrete.magicthegathering.data.model.mapper.mock

import com.concrete.magicthegathering.data.model.entity.cards.Card
import com.concrete.magicthegathering.data.model.entity.sets.Set

class MockSets{

    fun mockSetEntity(): Set {
        return Set(code = "10E", name = "name set", releaseDate = "date")
    }

    private fun mockCardEntity(position: Int): Card {
        return Card(imageUrl = "image card $position",
            name = "name card $position",
            id = position.toString(),
            types = listOf("water", "air"))
    }

    fun mockEntityList(): List<Card> {
        val cardList = ArrayList<Card>()
        for (i in 1..10) {
            cardList.add(mockCardEntity(i))
        }
        return cardList
    }
}