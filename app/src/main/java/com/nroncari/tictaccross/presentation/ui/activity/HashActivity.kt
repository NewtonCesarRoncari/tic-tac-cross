package com.nroncari.tictaccross.presentation.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import com.nroncari.tictaccross.R
import com.nroncari.tictaccross.databinding.ActivityHashBinding
import com.nroncari.tictaccross.presentation.model.GamePlayPresentation
import com.nroncari.tictaccross.presentation.model.GamePresentation
import com.nroncari.tictaccross.presentation.model.GameStatePresentation
import com.nroncari.tictaccross.presentation.model.TicToePresentation
import com.nroncari.tictaccross.presentation.viewmodel.GamePlayViewModel
import com.nroncari.tictaccross.presentation.viewmodel.SessionGameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HashActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {

    private val viewModel: SessionGameViewModel by viewModel()
    private val gamePlayViewModel: GamePlayViewModel by viewModel()
    private lateinit var ambientController: AmbientModeSupport.AmbientController
    private val binding by lazy { ActivityHashBinding.inflate(layoutInflater) }
    private val sessionGameCode by lazy { intent.extras!!.getString("CODE_SESSION")!! }
    private val listCircles by lazy {
        mapOf(
            0.0 to binding.circleA1,
            0.1 to binding.circleA2,
            0.2 to binding.circleA3,
            1.0 to binding.circleB1,
            1.1 to binding.circleB2,
            1.2 to binding.circleB3,
            2.0 to binding.circleC1,
            2.1 to binding.circleC2,
            2.2 to binding.circleC3
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        ambientController = AmbientModeSupport.attach(this)
        gamePlayViewModel.intConnectionWebSocket(sessionGameCode)
        setListeners()
    }

    @SuppressLint("CheckResult")
    private fun setListeners() {

        binding.token.text = sessionGameCode

        listCircles.forEach { map ->
            val resource =
                if (viewModel.getTicToe.value == 1) R.drawable.circle_item_blue else R.drawable.circle_item_red
            map.value.setOnClickListener {
                if (gamePlayViewModel.yourTime) {
                    markButton(map.value, resource)
                    val coordinate = map.key.toString().split(".")

                    gamePlayViewModel.sendGamePlay(
                        GamePlayPresentation(
                            gameId = sessionGameCode,
                            type = viewModel.getTicToe.desc,
                            coordinateX = coordinate.first().toInt(),
                            coordinateY = coordinate.last().toInt()
                        )
                    )
                    gamePlayViewModel.disableButtons()
                }
            }
        }

        binding.reset.setOnClickListener {
            gamePlayViewModel.playAgain()
        }

        gamePlayViewModel.isNewGame.observe(this, {
            clearBoard()
        })

        gamePlayViewModel.game.observe(this, { game ->
            gamePlayViewModel.checkStateGame(GameStatePresentation.NEW)
            gamePlayViewModel.configureYourTime(viewModel.getTicToe)
            fillBoard(game)
        })

        gamePlayViewModel.secondPlayerConnected.observe(this, {
            gamePlayViewModel.checkIAmFirstPlayer(TicToePresentation.X)
        })

        gamePlayViewModel.showSecondPlayerSnackBar.observe(this, {
            Toast.makeText(this, "Player 2 connected", Toast.LENGTH_LONG).show()
        })
    }

    private fun fillBoard(game: GamePresentation) {
        game.board.mapIndexed { x, ints ->
            ints.mapIndexed { y, ticTacToe ->
                val position = "$x.$y".toDouble()

                if (ticTacToe == TicToePresentation.O.value)
                    markButton(listCircles[position]!!, R.drawable.circle_item_blue)

                if (ticTacToe == TicToePresentation.X.value)
                    markButton(listCircles[position]!!, R.drawable.circle_item_red)
            }
        }
    }

    private fun clearBoard() {
        listCircles.values.forEach { circleButton ->
            circleButton.background = ContextCompat.getDrawable(
                this,
                R.drawable.circle_item
            )
            circleButton.isClickable = true
        }
        gamePlayViewModel.enableButtons()
    }

    private fun markButton(button: Button, resource: Int) {
        button.background = ContextCompat.getDrawable(this, resource)
        button.isClickable = false
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = HashAmbientCallback()
}

private class HashAmbientCallback : AmbientModeSupport.AmbientCallback() {

    override fun onEnterAmbient(ambientDetails: Bundle?) {
        // Handle entering ambient mode
    }

    override fun onExitAmbient() {
        // Handle exiting ambient mode
    }

    override fun onUpdateAmbient() {
        // Update the content
    }
}