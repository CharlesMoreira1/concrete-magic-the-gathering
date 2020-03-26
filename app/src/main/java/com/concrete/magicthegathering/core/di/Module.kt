package com.concrete.magicthegathering.core.di

import com.concrete.magicthegathering.data.source.local.AppDatabase
import com.concrete.magicthegathering.data.source.local.dao.FavoriteDao
import com.concrete.magicthegathering.data.source.remote.ApiClient
import com.concrete.magicthegathering.data.source.remote.api.ApiService
import com.concrete.magicthegathering.feature.detailcard.presentation.viewmodel.DetailCardViewModel
import com.concrete.magicthegathering.feature.detailcard.repository.DetailCardRepository
import com.concrete.magicthegathering.feature.detailcard.repository.IDetailCardRepository
import com.concrete.magicthegathering.feature.listset.presentation.viewmodel.SetViewModel
import com.concrete.magicthegathering.feature.listset.repository.ISetRepository
import com.concrete.magicthegathering.feature.listset.repository.SetRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<ISetRepository> { SetRepository(get()) }
    single<IDetailCardRepository> { DetailCardRepository(get()) }
}

val viewModelModule = module {
    viewModel<SetViewModel> { SetViewModel(get()) }
    viewModel<DetailCardViewModel> { DetailCardViewModel(get()) }
}

val apiServiceClientModule = module {
    single<ApiService> { ApiClient(get()).retrofit.create(ApiService::class.java) }
}

val databaseModule = module {
    single<AppDatabase> { AppDatabase.getInstance(context = get()) }
    single<FavoriteDao> { get<AppDatabase>().favoriteDao() }
}