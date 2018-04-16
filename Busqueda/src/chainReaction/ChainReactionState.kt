package chainReaction

data class CRCell(val color: Int, val shape: Int)

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

data class CRState(val game: CRGame, val touched: Map<Pair<Int, Int>, Boolean>, val last: Pair<Int, Int>)

data class CRRule(val direction: Direction, val steps: Int)

data class CRAction(val from: CRState, val to: CRState, val rule: CRRule)