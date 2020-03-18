package com.concrete.magicthegathering.feature.listset.repository

import com.concrete.magicthegathering.data.model.domain.SetDomain

interface ISetRepository {
    suspend fun getSetDomain(position: Int, isFirstRequest: Boolean): SetDomain
}