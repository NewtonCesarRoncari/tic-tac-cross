package com.nroncari.tictaccross.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nroncari.tictaccross.domain.usecase.ConnectGameUseCase
import com.nroncari.tictaccross.domain.usecase.CreateGameUseCase
import com.nroncari.tictaccross.presentation.model.GameConnexionPresentation
import com.nroncari.tictaccross.presentation.model.GamePresentation
import com.nroncari.tictaccross.presentation.model.TicToePresentation
import kotlinx.coroutines.*

class SessionGameViewModel(
    private val createGameUseCase: CreateGameUseCase,
    private val connectGameUseCase: ConnectGameUseCase
) : ViewModel() {


    private val launch = Job()
    private val scope = CoroutineScope(Dispatchers.IO + launch)

    private val _resultSuccess = MutableLiveData<Boolean?>().apply { value = false }
    val resultSuccess: LiveData<Boolean?> get() = _resultSuccess

    private val _game = MutableLiveData<GamePresentation?>()
    val game: LiveData<GamePresentation?> get() = _game

    val getTicToe get() = ticToe

    fun createGame() {
        scope.launch() {

            kotlin.runCatching {
                createGameUseCase()
            }.onSuccess { game ->
                ticToe = TicToePresentation.X
                _game.postValue(game)
                _resultSuccess.postValue(true)
            }.onFailure { error ->
                Log.e("Error", "Algo de errado não deu certo", error)
                _resultSuccess.postValue(false)
            }
        }
    }

    fun connectGame(gameConnexion: GameConnexionPresentation) {
        scope.launch() {

            kotlin.runCatching {
                connectGameUseCase(gameConnexion)
            }.onSuccess { game ->
                ticToe = TicToePresentation.O
                _game.postValue(game)
                _resultSuccess.postValue(true)
            }.onFailure { error ->
                Log.e("Error", "Algo de errado não deu certo", error)
                _resultSuccess.postValue(false)
            }
        }
    }

    companion object Single {
        lateinit var ticToe: TicToePresentation
    }

    override fun onCleared() {
        _resultSuccess.postValue(null)
        _game.postValue(null)
    }
}