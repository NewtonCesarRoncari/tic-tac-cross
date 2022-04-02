package com.nroncari.tictaccross

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Button
import androidx.core.content.ContextCompat
import com.nroncari.tictaccross.databinding.ActivityMainBinding

class MainActivity : WearableActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var toggleColor = true
    private val listCircles by lazy {
        listOf(
            binding.circleA1,
            binding.circleA2,
            binding.circleA3,
            binding.circleB1,
            binding.circleB2,
            binding.circleB3,
            binding.circleC1,
            binding.circleC2,
            binding.circleC3,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Enables Always-on
        setAmbientEnabled()
        setListeners()
    }

    private fun setListeners() {
        listCircles.forEach { markButton(it) }

        binding.reset.setOnClickListener {
            listCircles.forEach { circleButton ->
                circleButton.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.circle_item
                )
            }
        }
    }

    private fun markButton(button: Button) {
        button.setOnClickListener {
            button.background =
                ContextCompat.getDrawable(
                    this,
                    if (toggleColor) R.drawable.circle_item_blue else R.drawable.circle_item_red
                )
            toggleColor = !toggleColor
        }
    }
}