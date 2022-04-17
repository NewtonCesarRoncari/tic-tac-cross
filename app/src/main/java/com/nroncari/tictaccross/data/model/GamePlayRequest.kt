package com.nroncari.tictaccross.data.model

class GamePlayRequest(
        private val gameId: String,
        private val type: String,
        private val coordinateX: Int,
        private val coordinateY: Int,
)