package com.nroncari.tictaccross.domain.repository

import com.nroncari.tictaccross.data.model.GameConnexionRequest
import com.nroncari.tictaccross.presentation.model.GamePresentation

interface GameRepository {

    suspend fun createGame(): GamePresentation

    suspend fun connectGame(gameConnexion: GameConnexionRequest): GamePresentation
}