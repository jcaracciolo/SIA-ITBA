import ar.com.itba.sia.Heuristic
import chainReaction.game.CRRule
import chainReaction.game.CRState
import chainReaction.game.Direction
import chainReaction.heuristics.NeighbourFilter
import chainReaction.utils.CRParser
import org.junit.Test
import kotlin.test.assertEquals


class NeighbourTest {

    val base = "./test/resources/"

    @Test
    fun filterBlock() {
        val game = CRParser.parseBoard(base + "NeighbourTest")
        val state = game!!.getInitialState()
        val heuristic = NeighbourFilter(object : Heuristic<CRState> {
            override fun getValue(state: CRState): Double = 0.0
        })

        val firstRule = CRRule(Direction.RIGHT, 4)
        val firstState = firstRule.applyToState(state)
        assertEquals(0.0, heuristic.getValue(firstState))

        val secondRule = CRRule(Direction.LEFT, 3)
        val secondState = secondRule.applyToState(firstState)

        assertEquals(Double.POSITIVE_INFINITY, heuristic.getValue(secondState))
    }

    @Test
    fun filterPass() {
        val game = CRParser.parseBoard(base + "NeighbourTest")
        val state = game!!.getInitialState()
        val heuristic = NeighbourFilter(object : Heuristic<CRState> {
            override fun getValue(state: CRState): Double = 0.0
        })

        assertEquals(0.0,heuristic.getValue(state))
    }

    @Test
    fun filter2Posibilities() {
        val game = CRParser.parseBoard(base + "Neighbour2PosibilitiesTest")
        val state = game!!.getInitialState()
        val heuristic = NeighbourFilter(object : Heuristic<CRState> {
            override fun getValue(state: CRState): Double = 0.0
        })

        val firstRule = CRRule(Direction.RIGHT, 1)
        val firstState = firstRule.applyToState(state)
        assertEquals(Double.POSITIVE_INFINITY, heuristic.getValue(firstState))
    }

}