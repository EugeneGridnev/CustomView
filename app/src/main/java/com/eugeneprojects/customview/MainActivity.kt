package com.eugeneprojects.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eugeneprojects.customview.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ticTacToeView.ticTacToeField = TicTacToeField(10, 10)
        binding.randomFieldButton.setOnClickListener {
            binding.ticTacToeView.ticTacToeField = TicTacToeField(
                Random.nextInt(3, 10),
                Random.nextInt(3, 10)
            )
        }
    }
}