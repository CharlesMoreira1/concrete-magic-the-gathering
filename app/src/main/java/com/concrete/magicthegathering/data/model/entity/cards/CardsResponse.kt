package com.concrete.magicthegathering.data.model.entity.cards

import com.google.gson.annotations.SerializedName

data class CardsResponse(
    @SerializedName("cards")
    val cards: List<Card>
)