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
        val FROM_TERMINAL = false

        //Change to point to Board file location or generate one using BoardGenerator inside ChainReaction package
        var path = "./test/resources/here50x50bis-5-5"

        //Pick a searcher
        val bfsSearcher = BreadthFirstSearcher<CRState>()
        val dfsSearcher = DepthFirstSearcher<CRState>()

        var depthIncrease = 5
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
        fun main(args: Array<String>) {


            if(!FROM_TERMINAL) {
                val problem = CRParser.parseBoard(path)!!
                // |
                // v Change searcher here
                val solution = Engine<CRState>().solve(problem, astarSearcher)
                println(solution)

            }else {
                if(args.size < 2) {
                    throw IllegalArgumentException("Arguments required <Path> <Searcher> <SearcherParams>")
                }
                path = args[0]
                val problem = CRParser.parseBoard(path)!!

                val searcherString = args[1]
                val searcher: Searcher<CRState> = when (searcherString.toLowerCase()) {
                    "dfs" -> dfsSearcher
                    "bfs" -> bfsSearcher
                    "iddfs" -> {
                        if (args.size > 2) {
                            IterativeDeepening(args[2].toInt())
                        } else {
                            iterativeSearcher
                        }
                    }
                    "greed" -> {
                        if(args.size < 3) { throw IllegalArgumentException("Not a valid searcher") }
                        GreedySearch(getHeuristic(args[2]))
                    }
                    "astar" -> {
                        if(args.size < 3) { throw IllegalArgumentException("Not a valid searcher") }
                        AStar(getHeuristic(args[2]))
                    }
                    else -> throw IllegalArgumentException("Not a valid searcher")
                }

                val solution = Engine<CRState>().solve(problem, searcher)
                println(solution)
            }
        }

        fun getHeuristic(str: String) = when(str) {
            "basic" -> basicHeuristic
            "neighbour" -> neighbourHeuristic
            "composite" -> compositeHeuristic
            "compositeReverse" -> compositeReverseHeuristic
            "filteredBasic" -> filteredBasic
            "filteredNeighbour" -> filteredNeighbour
            "filteredComposite" -> filteredCompositeHeuristic
            "filteredCompositeReverse" -> filteredCompositeReverseHeuristic
            else -> throw IllegalArgumentException("Heuristic not valid")
        }
    }
}