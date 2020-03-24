package com.concrete.magicthegathering.data.model.domain

data class SetDomain(val nameSet: String, val listCardDomain: List<CardDomain>): ListSetDomain() {
    override val type: Int
        get() = ITEM_HEADER_SET

    fun isValid(): Boolean {
        return nameSet.isNotEmpty() && !listCardDomain.isNullOrEmpty()
    }
}