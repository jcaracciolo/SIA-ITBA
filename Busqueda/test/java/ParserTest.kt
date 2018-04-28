import chainReaction.game.CRBoard
import chainReaction.utils.CRParser
import chainReaction.utils.PairCache
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
        assertEquals(12, board.size)
        assertEquals(2, board.maxColor)
        assertEquals(3, board.maxShape)
        assertEquals(Pair(1,1), game.starting)
    }

    @Test
    fun emptyTest() {
        val game = CRParser.parseBoard(base + "emptyTest")
        assertNotNull(game)
        val board = game!!.board
        assertEquals(5, board.cols)
        assertEquals(2, board.rows)
        assertEquals(Pair(0,0), game.starting)
        assertEquals(3, board.maxColor)
        assertEquals(3, board.maxShape)
        assertEquals(3, board.maxShape)
        assertEquals(3, board.shapes.filter { it==CRBoard.EMPTY }.count())
        assertEquals(3, board.colors.filter { it==CRBoard.EMPTY }.count())

    }

    @Test(expected = IllegalArgumentException::class)
    fun noTokensTest() {
        CRParser.parseBoard(base + "noTokens")
    }

    @Test(expected = IllegalStateException::class)
    fun outOfBoundsInit() {
       CRParser.parseBoard(base + "outOfBoundsInit")
    }

    @Test(expected = IllegalStateException::class)
    fun wrongMaxTest() {
        CRParser.parseBoard(base + "wrongMaxTest")
    }

    @Test
    fun pairCacheTest(){
        val game = CRParser.parseBoard(base + "emptyTest")
        assertNotNull(game)
        val board = game!!.board
        assertEquals(5, board.cols)
        assertEquals(2, board.rows)
        (0 until board.rows).forEach { i ->
            (0 until board.cols).forEach {
                assertEquals(Pair(i, it), PairCache[i, it])
            }
        }
    }

    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun pairCacheFail(){
        val game = CRParser.parseBoard(base + "emptyTest")
        PairCache[game!!.board.rows, 0]
    }


}