import chainReaction.utils.CRParser
import junit.framework.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertNotNull

class ParserTest{
    private val inputPath = "./test/resources/smallBoardTest"

    @Test
    fun initTest() {
        val game = CRParser.parseBoard(inputPath)
        assertNotNull(game)
        val board = game!!.board
        assertEquals(4, board.cols)
        assertEquals(3, board.rows)
        assertEquals(2, board.maxColor)
        assertEquals(3, board.maxShape)
    }
}