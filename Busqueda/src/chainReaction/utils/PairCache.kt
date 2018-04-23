package chainReaction.utils

class PairCache {
    companion object {

        private var pairs: Array<Pair<Int, Int>> = Array(0) { Pair(0,0) }
        private var initialized = false
        private var rows: Int = 0
        private var cols: Int = 0

        fun initialize(rows: Int, cols: Int) {
            this.rows = rows
            this.cols = cols
            initialized = true

            pairs = Array( rows*cols) { index ->
                val i: Int = index / cols
                val j = index%cols
                return@Array Pair(i,j)
            }

        }

        operator fun get(i: Int, j: Int): Pair<Int, Int> {
            if(!initialized) throw IllegalStateException("Initialize the Pair Cache with initialize function")

            return pairs[i*cols + j]
        }
    }
}
