package chainReaction.utils

import java.util.*

class BooleanMatrix(override val rows: Int, override val cols: Int) : BuiltInMatrix<Boolean> {

    private var matrix = BooleanArray(rows*cols)
    private var trues: Int = 0

    constructor(other: BooleanMatrix) : this(other.rows, other.cols) {
        matrix = other.matrix.copyOf()
        trues = other.trues
    }

    fun count(value: Boolean) = when(value) {
        true -> trues
        false -> (rows*cols) - trues
    }

    override fun forEach(action: (Boolean) -> Unit) {
        matrix.forEach(action)
    }

    override fun filter(predicate: (Boolean) -> Boolean): List<Boolean> = matrix.filter(predicate)

    override operator fun get(i: Int): Boolean = matrix[i]

    override operator fun set(i: Int, value: Boolean) {
        val oldVal = matrix[i]
        if(oldVal != value) {
            matrix[i] = value
            if(value) {
                trues++
            } else {
                trues--
            }
        }
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(matrix)
    }

}