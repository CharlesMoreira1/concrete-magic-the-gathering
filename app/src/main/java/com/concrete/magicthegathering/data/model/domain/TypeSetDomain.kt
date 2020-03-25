package com.concrete.magicthegathering.data.model.domain

import com.concrete.magicthegathering.data.model.ItemType

class TypeSetDomain(val nameType: String) : ItemType() {
    override val type: Int
        get() = ITEM_HEADER_TYPE_CARD

    fun isValid(): Boolean {
        return nameType.isNotEmpty()
    }
}