package com.concrete.magicthegathering.core.di

import com.concrete.magicthegathering.data.source.remote.ApiClient
import org.koin.dsl.module

val apiClient = module {
    single<ApiClient> { ApiClient() }
}