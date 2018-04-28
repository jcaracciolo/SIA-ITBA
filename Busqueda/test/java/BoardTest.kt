import chainReaction.utils.CRParser
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class BoardTest {

    val base = "./test/resources/"

    @Test
    fun falseResolved() {
        val game = CRParser.parseBoard(base + "smallBoardTest")
        assertNotNull(game)
        assertFalse(game!!.isResolved(game.getInitialState()))
    }

    @Test
    fun resolved() {
        val game = CRParser.parseBoard(base + "oneToken")
        assertNotNull(game)
        assertTrue(game!!.isResolved(game.getInitialState()))
    }


    @Test(expected = IllegalStateException::class)
    fun emptyStartPoint() {
        CRParser.parseBoard(base + "emptyStarting")
    }


}