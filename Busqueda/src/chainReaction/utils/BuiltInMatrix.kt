package chainReaction.utils

interface BuiltInMatrix<B> {
    val rows: Int
    val cols: Int

    operator fun get(i: Int, j: Int): B = this[i*cols + j]
    operator fun set(i: Int, j: Int, value: B) {
        this[i*cols + j] = value
    }

    operator fun get(i: Int): B
    operator fun set(i: Int, value: B)
    operator fun get(p: Pair<Int, Int>): B = get(p.first, p.second)
    operator fun set(p: Pair<Int, Int>, value: B) = set(p.first, p.second, value)

    fun forEach(action: (B) -> kotlin.Unit)
    fun filter(predicate: (B) -> Boolean): List<B>
}