package com.concrete.magicthegathering.data.model.domain

data class CardDomain(val image: String?, val name: String, val multiverseid: Long, val typeName: String): ListSetDomain() {
    override val type: Int
        get() = ITEM_CARDS

    fun isValid(): Boolean {
        return !image.isNullOrEmpty() && name.isNotEmpty() && multiverseid > 0 && typeName.isNotEmpty()
    }
}