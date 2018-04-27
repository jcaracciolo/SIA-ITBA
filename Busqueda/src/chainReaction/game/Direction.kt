package chainReaction.game

import chainReaction.utils.PairCache

enum class Direction {
    UP {
        override fun move(pos: Pair<Int, Int>, steps: Int): Pair<Int, Int> = PairCache[pos.first - steps, pos.second]
    }, DOWN {
        override fun move(pos: Pair<Int, Int>, steps: Int): Pair<Int, Int> = PairCache[pos.first + steps, pos.second]
    }, LEFT {
        override fun move(pos: Pair<Int, Int>, steps: Int): Pair<Int, Int> = PairCache[pos.first, pos.second - steps]
    }, RIGHT {
        override fun move(pos: Pair<Int, Int>, steps: Int): Pair<Int, Int> = PairCache[pos.first, pos.second + steps]
    };

    abstract fun move(pos: Pair<Int, Int>, steps: Int): Pair<Int, Int>
}
