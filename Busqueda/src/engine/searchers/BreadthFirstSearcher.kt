package engine.searchers

import engine.Node
import java.util.*

class BreadthFirstSearcher <E> : Searcher<E> {
    private val visitedNodes: HashSet<Node<E>> = HashSet()

    var nodes: Queue<Node<E>> = ArrayDeque()

    override fun addNodes(nodes: List<Node<E>>, from: Node<E>) {
        this.nodes.addAll(nodes.filter{ !visitedNodes.contains(it) })
    }

    override fun isEmpty(): Boolean {
        return nodes.isEmpty()
    }

    override fun addNode(node: Node<E>) {
        nodes.add(node)
    }

    override fun nextNode(): Node<E> {
        val node = nodes.poll()
        visitedNodes.add(node)
        return node

    }

    override fun frontierNodes(): Int {
        return nodes.size
    }

}
