package com.nroncari.tictaccross.di

import com.nroncari.tictaccross.data.datasource.GamePlayRemoteDataSource
import com.nroncari.tictaccross.data.datasource.GamePlayRemoteDataSourceImpl
import com.nroncari.tictaccross.data.datasource.GameRemoteDataSource
import com.nroncari.tictaccross.data.datasource.GameRemoteDataSourceImpl
import com.nroncari.tictaccross.data.repository.GamePlayRepositoryImpl
import com.nroncari.tictaccross.data.repository.GameRepositoryImpl
import com.nroncari.tictaccross.data.retrofit.HttpClient
import com.nroncari.tictaccross.data.retrofit.RetrofitClient
import com.nroncari.tictaccross.data.service.GamePlayService
import com.nroncari.tictaccross.data.service.GameService
import com.nroncari.tictaccross.domain.repository.GamePlayRepository
import com.nroncari.tictaccross.domain.repository.GameRepository
import com.nroncari.tictaccross.domain.usecase.ConnectGameUseCase
import com.nroncari.tictaccross.domain.usecase.CreateGameUseCase
import com.nroncari.tictaccross.domain.usecase.GamePlayUseCase
import com.nroncari.tictaccross.domain.usecase.PlayAgainUseCase
import com.nroncari.tictaccross.presentation.viewmodel.GamePlayViewModel
import com.nroncari.tictaccross.presentation.viewmodel.SessionGameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val domainModules = module {
    factory { CreateGameUseCase(repository = get()) }
    factory { ConnectGameUseCase(repository = get()) }
    factory { GamePlayUseCase(repository = get()) }
    factory { PlayAgainUseCase(repository = get()) }
}

val presentationModules = module {
    single { SessionGameViewModel(createGameUseCase = get(), connectGameUseCase = get()) }
    viewModel { GamePlayViewModel(gamePlayUseCase = get(), playAgainUseCase = get()) }
}

val dataModules = module {
    factory<GameRemoteDataSource> { GameRemoteDataSourceImpl(service = get()) }
    factory<GamePlayRemoteDataSource> { GamePlayRemoteDataSourceImpl(service = get()) }
    factory<GameRepository> { GameRepositoryImpl(remoteDataSource = get()) }
    factory<GamePlayRepository> { GamePlayRepositoryImpl(remoteDataSource = get()) }
}

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(GameService::class.java) }
    factory { get<HttpClient>().create(GamePlayService::class.java) }
}