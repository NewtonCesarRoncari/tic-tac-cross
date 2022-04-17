package com.nroncari.tictaccross.data.repository

import com.nroncari.tictaccross.data.datasource.GamePlayRemoteDataSource
import com.nroncari.tictaccross.data.model.GamePlayRequest
import com.nroncari.tictaccross.domain.mapper.GameToPresentationMapper
import com.nroncari.tictaccross.domain.repository.GamePlayRepository
import com.nroncari.tictaccross.presentation.model.GamePresentation

class GamePlayRepositoryImpl(
    private val remoteDataSource: GamePlayRemoteDataSource
) : GamePlayRepository {

    private val mapper = GameToPresentationMapper()

    override suspend fun sendGamePlay(gamePlay: GamePlayRequest): GamePresentation {
        return mapper.map(remoteDataSource.sendGamePlay(gamePlay))
    }

    override suspend fun playAgain(gamePlay: GamePlayRequest): GamePresentation {
        return mapper.map(remoteDataSource.playAgain(gamePlay))
    }
}