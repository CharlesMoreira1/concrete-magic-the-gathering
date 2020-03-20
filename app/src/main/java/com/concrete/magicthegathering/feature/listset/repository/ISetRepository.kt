package com.concrete.magicthegathering.feature.listset.repository

import com.concrete.magicthegathering.data.model.domain.ListSetDomain

interface ISetRepository {
    suspend fun getListSetDomain(position: Int, isFirstRequest: Boolean): List<ListSetDomain>
}