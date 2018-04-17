package chainReaction

import ar.com.itba.sia.Rule

data class CRCell(val color: Int, val shape: Int)

enum class Direction {
    UP {
        override fun move(position: Pair<Int, Int>, steps: Int): Pair<Int, Int> = Pair(position.first - steps, position.second)
    }, DOWN {
        override fun move(position: Pair<Int, Int>, steps: Int): Pair<Int, Int> = Pair(position.first + steps, position.second)
    }, LEFT {
        override fun move(position: Pair<Int, Int>, steps: Int): Pair<Int, Int> = Pair(position.first, position.second - steps)
    }, RIGHT {
        override fun move(position: Pair<Int, Int>, steps: Int): Pair<Int, Int> = Pair(position.first, position.second + steps)
    };

    abstract fun move(position: Pair<Int, Int>, steps: Int): Pair<Int, Int>
}

data class CRAction(val from: CRState, val to: CRState, val rule: CRRule)