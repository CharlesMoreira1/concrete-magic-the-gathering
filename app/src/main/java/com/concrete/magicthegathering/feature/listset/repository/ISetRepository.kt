package com.concrete.magicthegathering.feature.listset.repository

import com.concrete.magicthegathering.data.model.ItemType

interface ISetRepository {
    suspend fun getListItemType(position: Int, isFirstRequest: Boolean): List<ItemType>
}