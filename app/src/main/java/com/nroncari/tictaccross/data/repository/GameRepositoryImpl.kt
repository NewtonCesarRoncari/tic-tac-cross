package com.nroncari.tictaccross.data.repository

import com.nroncari.tictaccross.data.datasource.GameRemoteDataSource
import com.nroncari.tictaccross.data.model.GameConnexionRequest
import com.nroncari.tictaccross.domain.mapper.GameToPresentationMapper
import com.nroncari.tictaccross.domain.repository.GameRepository
import com.nroncari.tictaccross.presentation.model.GamePresentation

class GameRepositoryImpl(
    private val remoteDataSource: GameRemoteDataSource
) : GameRepository {

    private val mapper = GameToPresentationMapper()

    override suspend fun createGame(): GamePresentation {
        return mapper.map(remoteDataSource.createGame())
    }

    override suspend fun connectGame(gameConnexion: GameConnexionRequest): GamePresentation {
        return mapper.map(remoteDataSource.connectGame(gameConnexion))
    }
}