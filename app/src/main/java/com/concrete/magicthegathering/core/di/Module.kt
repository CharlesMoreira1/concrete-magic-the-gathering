package com.concrete.magicthegathering.core.di

import com.concrete.magicthegathering.data.source.remote.ApiClient
import com.concrete.magicthegathering.data.source.remote.api.ApiService
import com.concrete.magicthegathering.feature.listset.presentation.viewmodel.SetViewModel
import com.concrete.magicthegathering.feature.listset.repository.ISetRepository
import com.concrete.magicthegathering.feature.listset.repository.SetRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<ISetRepository> { SetRepository(get()) }
}

val viewModelModule = module {
    viewModel<SetViewModel> { SetViewModel(get()) }
}

val apiServiceClientModule = module {
    single<ApiService> { ApiClient().retrofit.create(ApiService::class.java) }
}