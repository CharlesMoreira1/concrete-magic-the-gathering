package com.concrete.magicthegathering.data.model.domain

abstract class ItemAdapter {
    abstract val type: Int

    companion object {
        const val ITEM_HEADER_SET = 0
        const val ITEM_HEADER_TYPE_CARD = 1
        const val ITEM_CARDS = 2
    }
}