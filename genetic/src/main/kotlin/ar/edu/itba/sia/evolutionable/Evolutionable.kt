package ar.edu.itba.sia.evolutionable.characters

interface Evolutionable<G> {

    fun getDescendant(): Evolutionable<G>

    fun getPerformance(): Double

    fun mutateGen(n: Int)

    val gens: Array<G>

}