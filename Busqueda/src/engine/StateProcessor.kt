package engine

interface StateProcessor<E> {
    fun proces(state: E)
}