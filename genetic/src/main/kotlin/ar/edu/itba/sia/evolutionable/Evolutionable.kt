package ar.edu.itba.sia.evolutionable

interface Evolutionable {

    fun getDescendant(): Evolutionable

    fun getPerformance(): Double

    fun mutateGen(n: Int)

    val gens: Array<Any>

}