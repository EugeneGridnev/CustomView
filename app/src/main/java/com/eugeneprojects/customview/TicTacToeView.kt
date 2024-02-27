package com.eugeneprojects.customview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.GridView
import kotlin.properties.Delegates


class TicTacToeView(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributesSet, defStyleAttr, defStyleRes) {

    var ticTacToeField: TicTacToeField? = null
        set(value) {
            field?.liseners?.remove(listener)
            field = value
            field?.liseners?.add(listener)
            requestLayout()
            invalidate()
        }

    private var player1Color by Delegates.notNull<Int>()
    private var player2Color by Delegates.notNull<Int>()
    private var gridColor by Delegates.notNull<Int>()

    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, R.style.DefaultTicTacToeFieldStyle)
    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, R.attr.ticTacToeFieldStyle)
    constructor(context: Context) : this(context, null)

    init {
        if (attributesSet != null) {
            initAttributes(attributesSet, defStyleAttr, defStyleRes)
        } else {
            initDefaultColor()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        ticTacToeField?.liseners?.add(listener)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        ticTacToeField?.liseners?.remove(listener)
    }

    private fun initAttributes(attributesSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(attributesSet, R.styleable.TicTacToeView, defStyleAttr, defStyleRes)
        player1Color = typedArray.getColor(R.styleable.TicTacToeView_player1Color, PLAYER1_DEFAULT_COLOR)
        player2Color = typedArray.getColor(R.styleable.TicTacToeView_player2Color, PLAYER2_DEFAULT_COLOR)
        gridColor = typedArray.getColor(R.styleable.TicTacToeView_gridColor, GRID_DEFAULT_COLOR)

        typedArray.recycle()
    }

    private fun initDefaultColor() {
        player1Color = PLAYER1_DEFAULT_COLOR
        player2Color = PLAYER2_DEFAULT_COLOR
        gridColor = GRID_DEFAULT_COLOR
    }

    private val listener: OnFieldChangedListener = {

    }

    companion object {
        const val PLAYER1_DEFAULT_COLOR = Color.GREEN
        const val PLAYER2_DEFAULT_COLOR = Color.RED
        const val GRID_DEFAULT_COLOR = Color.GRAY
    }
}