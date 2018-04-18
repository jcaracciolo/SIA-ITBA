package engine.searchers

import engine.Node

interface Searcher <E>{

    fun nextNode(): Node<E>

    fun addNodes(nodes: List<Node<E>>)

    fun addNode(node: Node<E>)

    fun isEmpty(): Boolean
}