package com.concrete.magicthegathering.data.model.domain

class TypeSetDomain(val nameType: String) : ItemAdapter() {
    override val type: Int
        get() = ITEM_HEADER_TYPE_CARD

    fun isValid(): Boolean {
        return nameType.isNotEmpty()
    }
}