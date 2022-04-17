package com.nroncari.tictaccross.data.service

import com.nroncari.tictaccross.data.model.GamePlayRequest
import com.nroncari.tictaccross.data.model.GameResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GamePlayService {

    @POST("gameplay/")
    suspend fun sendGamePlay(@Body gamePlay: GamePlayRequest): GameResponse

    @POST("playagain")
    suspend fun playAgain(@Body gamePlay: GamePlayRequest): GameResponse
}