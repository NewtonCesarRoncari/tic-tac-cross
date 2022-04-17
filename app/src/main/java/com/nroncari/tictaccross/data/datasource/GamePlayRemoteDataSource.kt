package com.nroncari.tictaccross.data.datasource

import com.nroncari.tictaccross.data.model.GamePlayRequest
import com.nroncari.tictaccross.domain.model.GameDomain

interface GamePlayRemoteDataSource {

    suspend fun sendGamePlay(gamePlay: GamePlayRequest): GameDomain

    suspend fun playAgain(gamePlay: GamePlayRequest): GameDomain
}