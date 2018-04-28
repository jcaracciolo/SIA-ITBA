import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState
import chainReaction.utils.CRParser
import engine.Engine
import org.junit.Assert
import org.junit.Test

class EngineTest {

    val base = "./test/resources/"

    @Test
    fun TrivialBFSTest(){
        val problem = CRParser.parseBoard(base + "trivialBoard")!!
        val solution = Engine<CRState>().solve(problem, object : Heuristic<CRState> {
            override fun getValue(state: CRState): Double {
                return 1.0
            }
        })
       Assert.assertNotNull(solution)
    }

//    @Test
//    fun GeneratedTest(){
//        val problem = CRParser.parseBoard(base + "here")!!
//        val solution = Engine<CRState>().solve(problem, object : Heuristic<CRState> {
//            override fun getValue(state: CRState): Double {
//                return 1.0
//            }
//        })
//        Assert.assertNotNull(solution)
//    }

    @Test
    fun Impossible2x2BFSTest(){
        val problem = CRParser.parseBoard(base + "impossible2x2Board")!!
        val solution = Engine<CRState>().solve(problem, object : Heuristic<CRState> {
            override fun getValue(state: CRState): Double {
                return 0.0
            }
        })
        Assert.assertNull(solution)
    }
}