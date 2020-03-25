package com.concrete.magicthegathering.data.model.domain

import com.concrete.magicthegathering.data.model.ItemType

data class CardDomain(val image: String?, val name: String, val idCard: String, val typeName: String): ItemType() {
    override val type: Int
        get() = ITEM_CARDS

    fun isValid(): Boolean {
        return !image.isNullOrEmpty() && name.isNotEmpty() && idCard.isNotEmpty() && typeName.isNotEmpty()
    }
}