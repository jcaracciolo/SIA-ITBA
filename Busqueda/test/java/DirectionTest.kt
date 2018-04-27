import chainReaction.game.Direction
import chainReaction.utils.PairCache
import org.junit.Test
import junit.framework.Assert.assertEquals
import org.junit.Before

class DirectionTest {

    @Before
    fun before() {
        PairCache.initialize(5,5)
    }

    @Test
    fun leftTest(){
        assertEquals(Pair(2,0), Direction.LEFT.move(Pair(2,2), 2))
    }

    @Test
    fun rightTest(){
        assertEquals(Pair(2,4), Direction.RIGHT.move(Pair(2,2), 2))
    }

    @Test
    fun upTest(){
        assertEquals(Pair(0,2), Direction.UP.move(Pair(2,2), 2))
    }

    @Test
    fun downTest(){
        assertEquals(Pair(4,2), Direction.DOWN.move(Pair(2,2), 2))
    }

    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun outOfBounds(){
        Direction.UP.move(Pair(0,0), 1)
    }

}