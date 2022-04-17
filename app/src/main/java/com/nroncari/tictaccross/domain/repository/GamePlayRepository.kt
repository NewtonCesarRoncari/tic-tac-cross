package com.nroncari.tictaccross.domain.repository

import com.nroncari.tictaccross.data.model.GamePlayRequest
import com.nroncari.tictaccross.presentation.model.GamePresentation

interface GamePlayRepository {

    suspend fun sendGamePlay(gamePlay: GamePlayRequest): GamePresentation

    suspend fun playAgain(gamePlay: GamePlayRequest): GamePresentation
}