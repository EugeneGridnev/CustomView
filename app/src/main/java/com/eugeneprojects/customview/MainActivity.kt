package com.eugeneprojects.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eugeneprojects.customview.databinding.ActivityMainBinding
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isFirstPlayer by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val field = savedInstanceState?.getParcelable<TicTacToeField.Memento>(KEY_FIELD)?.restoreField() ?:
        TicTacToeField(10, 10)
        binding.ticTacToeView.ticTacToeField = field
        isFirstPlayer = savedInstanceState?.getBoolean(KEY_IS_FIRST_PLAYER, true) ?: true

        binding.ticTacToeView.actionListener = { row, column, currentField ->
            val cell = currentField.getCell(row, column)
            if (cell == Cell.EMPTY) {
                if (isFirstPlayer) {
                    currentField.setCell(row, column, Cell.PLAYER_1)
                } else {
                    currentField.setCell(row, column, Cell.PLAYER_2)
                }
                isFirstPlayer = !isFirstPlayer
            }
        }

        binding.randomFieldButton.setOnClickListener {
            binding.ticTacToeView.ticTacToeField = TicTacToeField(
                Random.nextInt(3, 10),
                Random.nextInt(3, 10)
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val field = binding.ticTacToeView.ticTacToeField
        outState.putParcelable(KEY_FIELD, field!!.saveState())
        outState.putBoolean(KEY_IS_FIRST_PLAYER, isFirstPlayer)
    }

    companion object {
        private const val KEY_FIELD = "KEY_FIELD"
        private const val KEY_IS_FIRST_PLAYER = "KEY_IS_FIRST_PLAYER"
    }
}