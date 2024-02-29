package com.eugeneprojects.customview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

enum class Cell {
    PLAYER_1,
    PLAYER_2,
    EMPTY
}

typealias OnFieldChangedListener = (field: TicTacToeField) -> Unit

class TicTacToeField  private  constructor(val rows: Int, val columns: Int, private val cells: Array<Array<Cell>>) {

    val listeners : MutableSet<OnFieldChangedListener> = mutableSetOf()

    constructor(rows: Int, columns: Int) : this(
        rows,
        columns,
        Array(rows) { Array(columns) { Cell.EMPTY } }
    )
    fun getCell(row: Int, column: Int): Cell {
        if (row < 0 || column < 0 || row >= rows || column >= columns) return Cell.EMPTY
        return cells[row][column]
    }

    fun setCell(row: Int, column: Int, cell: Cell) {
        if (row < 0 || column < 0 || row >= rows || column >= columns) return
        if (cells[row][column] != cell ) {
            cells[row][column] = cell
            listeners.forEach { it?.invoke(this) }
        }
    }

    fun saveState(): Memento {
        return Memento(rows, columns, cells)
    }

    @Suppress("ArrayInDataClass")
    @Parcelize
    data class Memento(
        private val rows: Int,
        private val columns: Int,
        private val cells: Array<Array<Cell>>
    ) : Parcelable {
        fun restoreField(): TicTacToeField {
            return TicTacToeField(rows, columns, cells)
        }
    }
}