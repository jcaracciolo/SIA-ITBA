package chainReaction.utils

class CharMatrix(override val rows: Int, override val cols: Int) : BuiltInMatrix<Char> {

    private var matrix = CharArray(rows*cols)

    constructor(other: CharMatrix) : this(other.rows, other.cols) {
        matrix = other.matrix.copyOf()
    }

    override fun get(i: Int): Char = matrix[i]
    override fun set(i: Int, value: Char) { matrix[i] = value }
}