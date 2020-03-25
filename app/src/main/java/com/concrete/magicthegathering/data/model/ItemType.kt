package com.concrete.magicthegathering.data.model

abstract class ItemType {
    abstract val type: Int

    companion object {
        const val ITEM_HEADER_SET = 0
        const val ITEM_HEADER_TYPE_CARD = 1
        const val ITEM_CARDS = 2
    }
}