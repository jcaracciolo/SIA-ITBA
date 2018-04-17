package chainReaction

import ar.com.itba.sia.Rule
import utils.PairCache

data class CRCell(val color: Int, val shape: Int)

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

data class CRAction(val from: CRState, val to: CRState, val rule: CRRule)