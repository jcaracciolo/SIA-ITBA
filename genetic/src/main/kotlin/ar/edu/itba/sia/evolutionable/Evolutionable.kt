package ar.edu.itba.sia.evolutionable.characters

interface Evolutionable<G> {

    fun getDescendant(): Evolutionable<G>

    fun getPerformance(): Double

    fun mutateGen(n: Int)

    fun setGen(n: Int, value: G)

    fun getGen(n: Int): G

    fun genSize(): Int


}