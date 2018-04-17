package chainReaction

import utils.BooleanMatrix

data class CRState(val game: CRGame, val touched: BooleanMatrix, val last: Pair<Int, Int>)