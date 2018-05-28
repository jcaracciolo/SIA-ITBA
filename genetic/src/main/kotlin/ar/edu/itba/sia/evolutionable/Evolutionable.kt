package ar.edu.itba.sia.evolutionable


interface Evolutionable {

    fun getDescendant(): Evolutionable

    fun getPerformance(): Double

    fun mutateGen(n: Int)

    fun random(): Evolutionable

    fun isValid(): Boolean

    val gens: Array<Any>

}