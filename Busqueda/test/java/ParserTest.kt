import chainReaction.game.CRBoard
import chainReaction.utils.CRParser
import junit.framework.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertNotNull

class ParserTest{

    val base = "./test/resources/"

    @Test
    fun initTest() {
        val game = CRParser.parseBoard(base + "smallBoardTest")
        assertNotNull(game)
        val board = game!!.board
        assertEquals(4, board.cols)
        assertEquals(3, board.rows)
        assertEquals(2, board.maxColor)
        assertEquals(3, board.maxShape)
    }

    @Test
    fun emptyTest() {
        val game = CRParser.parseBoard(base + "emptyTest")
        assertNotNull(game)
        val board = game!!.board
        assertEquals(5, board.cols)
        assertEquals(2, board.rows)
        assertEquals(3, board.maxColor)
        assertEquals(3, board.maxShape)
        assertEquals(3, board.maxShape)
        assertEquals(3, board.shapes.filter { it==CRBoard.EMPTY }.count())
        assertEquals(3, board.colors.filter { it==CRBoard.EMPTY }.count())

    }
}