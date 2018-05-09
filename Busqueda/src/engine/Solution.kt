package engine

class Solution <E> {

    private val initialTime = System.currentTimeMillis()
    var finalTime = System.currentTimeMillis()
    var solved: Boolean = false
    var nodesCreated: Int = 0
    var expandedNodes: Int = 0
    var frontierNodes: Int = 0
    var solutionDepth: Int = 0
    var solutionCost: Double = 0.0
    var solution: List<Node<E>>? = null

    override fun toString(): String {
        if (!solved)
            return "No Solution was found\nNodes created: ${nodesCreated}, ExpandedNodes: ${expandedNodes} and took ${finalTime-initialTime} miliseconds "
        return ("Solution Depth: ${solutionDepth} and Cost: ${solutionCost} and took ${finalTime-initialTime} miliseconds \n" +
                "Nodes created: ${nodesCreated}, FrontierNodes: ${frontierNodes}, ExpandedNodes: ${expandedNodes} \n" +
            "Solution: \n ${solution!!.first()}")
    }
}