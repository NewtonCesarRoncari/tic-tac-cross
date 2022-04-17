package com.nroncari.tictaccross.data.datasource

import com.nroncari.tictaccross.data.model.GameConnexionRequest
import com.nroncari.tictaccross.domain.model.GameDomain

interface GameRemoteDataSource {

    suspend fun createGame(): GameDomain

    suspend fun connectGame(gameConnexion: GameConnexionRequest): GameDomain
}