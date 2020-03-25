package com.concrete.magicthegathering.data.model.domain

import com.concrete.magicthegathering.data.model.ItemType

data class SetDomain(val nameSet: String, val listCardDomain: List<CardDomain>): ItemType() {
    override val type: Int
        get() = ITEM_HEADER_SET

    fun isValid(): Boolean {
        return nameSet.isNotEmpty() && !listCardDomain.isNullOrEmpty()
    }
}