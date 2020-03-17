package com.concrete.magicthegathering.data.model.entity.cards

import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("cards")
    val cards: List<Card>
)