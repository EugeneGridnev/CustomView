package com.eugeneprojects.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View


class TicTacToeView(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributesSet, defStyleAttr, defStyleRes) {
    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, 0)
    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, 0)
    constructor(context: Context) : this(context, null)

    init {
        if (attributesSet != null) {
            initAttributes(attributesSet, defStyleAttr, defStyleRes)
        }
    }

    private fun initAttributes(attributesSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {

    }
}