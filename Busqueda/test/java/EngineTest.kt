import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState
import chainReaction.heuristics.*
import chainReaction.utils.CRParser
import engine.Engine
import engine.searchers.*
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertFalse

class EngineTest {

    val base = "./test/resources/"

    @Test
    fun TrivialBFSTest(){
        val problem = CRParser.parseBoard(base + "trivialBoard")!!
        val solution = Engine<CRState>().solve(problem, DepthFirstSearcher())
        println(solution)
        assertTrue(solution.solved)
    }

    @Test
    fun GenerateBFSdTest(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, BreadthFirstSearcher())
        println(solution)
        assertTrue(solution.solved)
    }

    @Test
    fun GeneratedDFSTest(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, DepthFirstSearcher())
        println(solution)
        assertTrue(solution.solved)
    }

    @Test
    fun GeneratedAStartTest(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, AStar(CloserHeuristic()))
        println(solution)
        Assert.assertNotNull(solution)
    }

    @Test
    fun GeneratedGreedyTest(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, GreedySearch(CloserHeuristic()))
        println(solution)
        assertTrue(solution.solved)
    }

    @Test
    fun GeneratedIterativeDeepeningSearch(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, IterativeDeepening(3))
        println(solution)
        assertTrue(solution.solved)
    }

    @Test
    fun GeneratedDFSHUgeTest(){
        val problem = CRParser.parseBoard(base + "HugeMap")!!
        val solution = Engine<CRState>().solve(problem, AStar(NeighbourFilter(
                ComposeHeuristic.composite(CloserHeuristic(), NeighboursHeuristic())
        )))
        println(solution)
        assertTrue(solution.solved)
    }


    @Test
    fun Impossible2x2BFSTest(){
        val problem = CRParser.parseBoard(base + "impossible2x2Board")!!
        val solution = Engine<CRState>().solve(problem, DepthFirstSearcher())
        println(solution)
        assertFalse(solution.solved)
    }

    @Test
    fun Boardchecker(){
        val problem = CRParser.parseBoard(base + "here8x8-5-5")!!
        val solution = Engine<CRState>().solve(problem, AStar(NeighbourFilter(
                ComposeHeuristic.composite(CloserHeuristic(), NeighboursHeuristic())
        )))
        println(solution)
        assertTrue(solution.solved)
    }

    @Test
    fun testingBFSBIG(){
        val problem = CRParser.parseBoard(base + "here3x3-5-5")!!
        val solution = Engine<CRState>().solve(problem, DepthFirstSearcher())
        print("DFS  \n" + solution)
        val solution2 = Engine<CRState>().solve(problem, BreadthFirstSearcher())
        print("BFS  \n" + solution2)
        val solution3 = Engine<CRState>().solve(problem, IterativeDeepening(5))
        print("IDDFS  \n" + solution3)
        val solution4 = Engine<CRState>().solve(problem, DepthFirstSearcher())
        print("Greedy  \n" + solution4)
        Assert.assertNotNull(solution)
    }

}