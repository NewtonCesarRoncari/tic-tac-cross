package com.nroncari.tictaccross.data.datasource

import com.nroncari.tictaccross.data.mapper.GameToDomainMapper
import com.nroncari.tictaccross.data.model.GameConnexionRequest
import com.nroncari.tictaccross.data.service.GameService
import com.nroncari.tictaccross.domain.model.GameDomain

class GameRemoteDataSourceImpl(
    private val service: GameService
) : GameRemoteDataSource {

    private val gameMapper = GameToDomainMapper()

    override suspend fun createGame(): GameDomain {
        return gameMapper.map(service.createGame())
    }

    override suspend fun connectGame(gameConnexion: GameConnexionRequest): GameDomain {
        return gameMapper.map(service.connectGame(gameConnexion))
    }
}