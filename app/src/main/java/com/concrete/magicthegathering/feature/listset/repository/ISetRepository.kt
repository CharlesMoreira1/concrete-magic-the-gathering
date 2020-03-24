package com.concrete.magicthegathering.feature.listset.repository

import com.concrete.magicthegathering.data.model.domain.ItemAdapter

interface ISetRepository {
    suspend fun getListSetDomain(position: Int, isFirstRequest: Boolean): List<ItemAdapter>
}