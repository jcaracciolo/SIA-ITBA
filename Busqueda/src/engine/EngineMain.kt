package engine

import chainReaction.game.CRState
import chainReaction.heuristics.CloserHeuristic
import chainReaction.heuristics.ComposeHeuristic
import chainReaction.heuristics.NeighbourFilter
import chainReaction.heuristics.NeighboursHeuristic
import chainReaction.utils.CRParser
import engine.searchers.*

class EngineMain {
    companion object {
        //Change to point to Board file location or generate one using BoardGenerator inside ChainReaction package
        val base = "./test/resources/"
        val fileName = "here"

        val problem = CRParser.parseBoard(base + fileName)!!

        //Pick a searcher
        val bfsSearcher = BreadthFirstSearcher<CRState>()
        val dfsSearcher = DepthFirstSearcher<CRState>()

        val depthIncrease = 5
        val iterativeSearcher = IterativeDeepening<CRState>(depthIncrease)

        val basicHeuristic = ComposeHeuristic.composite(CloserHeuristic())
        val neighbourHeuristic = ComposeHeuristic.composite(NeighboursHeuristic())
        val compositeHeuristic = ComposeHeuristic.composite(CloserHeuristic(), NeighboursHeuristic())
        val compositeReverseHeuristic = ComposeHeuristic.composite(NeighboursHeuristic(), CloserHeuristic())

        val filteredBasic = NeighbourFilter(basicHeuristic)
        val filteredNeighbour = NeighbourFilter(neighbourHeuristic)
        val filteredCompositeHeuristic = NeighbourFilter(compositeHeuristic) // BEST HEURISTIC
        val filteredCompositeReverseHeuristic = NeighbourFilter(compositeReverseHeuristic)

        // Pick an heuristic for these informed searchers
        val greedySearcher = GreedySearch(filteredCompositeHeuristic)
        val astarSearcher = AStar(filteredCompositeHeuristic)


        @JvmStatic
        fun main(args: Array<String>) {                     // |
                                                            // v Change searcher here
            val solution = Engine<CRState>().solve(problem, astarSearcher)
            println(solution)
        }
    }
}