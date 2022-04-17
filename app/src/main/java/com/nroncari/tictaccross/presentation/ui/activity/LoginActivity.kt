package com.nroncari.tictaccross.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import com.nroncari.tictaccross.databinding.ActivityLoginBinding
import com.nroncari.tictaccross.presentation.viewmodel.SessionGameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {

    private lateinit var ambientController: AmbientModeSupport.AmbientController
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
     private val viewModel: SessionGameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ambientController = AmbientModeSupport.attach(this)
        listeners()
    }

    private fun listeners() {
        binding.create.setOnClickListener {
            Log.e("Error", "lisetener")
            initLoading()
            viewModel.createGame()
        }
        binding.connect.setOnClickListener {
            goToConnectActivity()
        }
        viewModel.resultSuccess.observe(this, { resultSuccess ->
            if (resultSuccess == true) {
                goToHashActivity()
            }
            finishLoading()
        })
    }

    private fun goToConnectActivity() {
        startActivity(Intent(this, ConnectActivity::class.java))
    }

    private fun goToHashActivity() {
        val intent = Intent(this, HashActivity::class.java)
        intent.putExtra("CODE_SESSION", viewModel.game.value!!.id)
        startActivity(intent)
    }

    private fun initLoading() {
        binding.loginProgressbar.visibility = View.VISIBLE
    }

    private fun finishLoading() {
        binding.loginProgressbar.visibility = View.GONE
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = LoginAmbientCallback()
}

private class LoginAmbientCallback : AmbientModeSupport.AmbientCallback() {

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