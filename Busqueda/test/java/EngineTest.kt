import ar.com.itba.sia.Heuristic
import chainReaction.game.CRState
import chainReaction.heuristics.*
import chainReaction.utils.CRParser
import engine.Engine
import engine.searchers.*
import org.junit.Assert
import org.junit.Test

class EngineTest {

    val base = "./test/resources/"

    @Test
    fun TrivialBFSTest(){
        val problem = CRParser.parseBoard(base + "trivialBoard")!!
        val solution = Engine<CRState>().solve(problem, DepthFirstSearcher())
       Assert.assertNotNull(solution)
    }

    @Test
    fun GenerateBFSdTest(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, BreadthFirstSearcher())
        Assert.assertNotNull(solution)
    }

    @Test
    fun GeneratedDFSTest(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, DepthFirstSearcher())
        Assert.assertNotNull(solution)
    }

    @Test
    fun GeneratedAStartTest(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, AStar(CloserHeuristic()))
        Assert.assertNotNull(solution)
    }

    @Test
    fun GeneratedGreedyTest(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, GreedySearch(CloserHeuristic()))
        Assert.assertNotNull(solution)
    }

    @Test
    fun GeneratedIterativeDeepeningSearch(){
        val problem = CRParser.parseBoard(base + "here")!!
        val solution = Engine<CRState>().solve(problem, IterativeDeepening(3))
        Assert.assertNotNull(solution)
    }

    @Test
    fun GeneratedDFSHUgeTest(){
        val problem = CRParser.parseBoard(base + "HugeMap")!!
        val solution = Engine<CRState>().solve(problem, AStar(NeighbourFilter(
                ComposeHeuristic.composite(CloserHeuristic(), NeighboursHeuristic())
        )))
        Assert.assertNotNull(solution)
    }


    @Test
    fun Impossible2x2BFSTest(){
        val problem = CRParser.parseBoard(base + "impossible2x2Board")!!
        val solution = Engine<CRState>().solve(problem, DepthFirstSearcher())
        Assert.assertNull(solution)
    }

    @Test
    fun Boardchecker(){
        val problem = CRParser.parseBoard(base + "here8x8-5-5")!!
        val solution = Engine<CRState>().solve(problem, AStar(NeighbourFilter(
                ComposeHeuristic.composite(CloserHeuristic(), NeighboursHeuristic())
        )))
        print(solution)
        Assert.assertNotNull(solution)
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