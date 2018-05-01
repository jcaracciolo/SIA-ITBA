package engine.searchers

import engine.Node
import java.util.*

class DepthFirstSearcher<E>: Searcher<E> {
    private val visitedNodes: HashSet<Node<E>> = HashSet()

    var nodes:Stack<Node<E>> = Stack()

    override fun nextNode(): Node<E> {
        val node = nodes.pop()
        visitedNodes.add(node)
        return node
    }

    override fun addNodes(nodes: List<Node<E>>, from: Node<E>) {
        this.nodes.addAll(nodes.filter{ !visitedNodes.contains(it) })
    }

    override fun addNode(node: Node<E>) {
        nodes.add(node)
    }

    override fun isEmpty(): Boolean {
        return nodes.isEmpty()
    }
}