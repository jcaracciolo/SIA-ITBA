package utils

interface BuiltInMatrix<B> {
    val rows: Int
    val cols: Int

    operator fun get(i: Int, j: Int): B = this[i*rows + j]
    operator fun set(i: Int, j: Int, value: B) = { this[i*rows + j] = value }

    operator fun get(i: Int): B
    operator fun set(i: Int, value: B)

}