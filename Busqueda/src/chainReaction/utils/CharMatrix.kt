package chainReaction.utils

import java.util.*

class CharMatrix(override val rows: Int, override val cols: Int) : BuiltInMatrix<Char> {

    private var matrix = CharArray(rows*cols)

    constructor(other: CharMatrix) : this(other.rows, other.cols) {
        matrix = other.matrix.copyOf()
    }

    override fun get(i: Int): Char = matrix[i]
    override fun set(i: Int, value: Char) {
        matrix[i] = value
    }

    override fun forEach(action: (Char) -> Unit) {
        matrix.forEach(action)
    }

    override fun filter(predicate: (Char) -> Boolean): List<Char> = matrix.filter(predicate)

    override fun toString(): String {
        val str = StringBuilder()
        (0 until rows).forEach { i->
            (0 until cols).forEach { j->
                str.append(" ${this[i,j].toInt()}\t - ")
            }
            str.append("\n")
        }

        return str.toString()
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(matrix)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharMatrix

        if (!Arrays.equals(matrix, other.matrix)) return false

        return true
    }

}