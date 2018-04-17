package chainReaction

data class CRState(val game: CRGame, val touched: Map<Pair<Int, Int>, Boolean>, val last: Pair<Int, Int>)