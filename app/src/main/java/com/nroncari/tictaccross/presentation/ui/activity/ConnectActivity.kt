package com.nroncari.tictaccross.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import com.nroncari.tictaccross.databinding.ActivityConnectBinding
import com.nroncari.tictaccross.presentation.model.GameConnexionPresentation
import com.nroncari.tictaccross.presentation.viewmodel.SessionGameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConnectActivity: FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {

    private lateinit var ambientController: AmbientModeSupport.AmbientController
    private val binding by lazy { ActivityConnectBinding.inflate(layoutInflater) }
    private val viewModel: SessionGameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ambientController = AmbientModeSupport.attach(this)
        listeners()
    }

    private fun listeners() {
        binding.connect.setOnClickListener {
            initLoading()
            viewModel.connectGame(
                GameConnexionPresentation(binding.connectCode.text.toString().trim())
            )
            viewModel.resultSuccess.observe(this, { resultSuccess ->
                if (resultSuccess == true) {
                    goToHashFragment(binding.connectCode.text.toString().trim())
                }
                finishLoading()
            })
        }
    }

    private fun goToHashFragment(sessionGameCode: String) {
        val intent = Intent(this, HashActivity::class.java)
        intent.putExtra("CODE_SESSION", sessionGameCode)
        startActivity(intent)
    }

    private fun initLoading() {
        binding.connectProgressbar.visibility = View.VISIBLE
    }

    private fun finishLoading() {
        binding.connectProgressbar.visibility = View.GONE
    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = ConnectAmbientCallback()
}

private class ConnectAmbientCallback : AmbientModeSupport.AmbientCallback() {

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