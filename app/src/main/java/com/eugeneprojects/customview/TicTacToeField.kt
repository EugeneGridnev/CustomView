package com.eugeneprojects.customview

enum class Cell {
    PLAYER_1,
    PLAYER_2,
    EMPTY
}

typealias OnFieldChangedListener = (field: TicTacToeField) -> Unit

class TicTacToeField (val rows: Int, val columns: Int) {
    private val cells : Array<Array<Cell>> = Array(rows) { Array(columns) { Cell.EMPTY } }

    val liseners : MutableSet<OnFieldChangedListener> = mutableSetOf()
    fun getCell(row: Int, column: Int): Cell {
        if (row < 0 || column < 0 || row >= rows || column >= columns) return Cell.EMPTY
        return cells[row][column]
    }

    fun setCell(row: Int, column: Int, cell: Cell) {
        if (row < 0 || column < 0 || row >= rows || column >= columns) return
        if (cells[row][column] != cell ) {
            cells[row][column] = cell
            liseners.forEach { it.invoke(this) }
        }
    }
}