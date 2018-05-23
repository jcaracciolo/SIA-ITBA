package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class AdaptedEvolutionable (val newPerformance: Double, val specimen: Evolutionable) : Evolutionable {
    override fun getDescendant(): Evolutionable = specimen.getDescendant()

    override fun getPerformance(): Double = newPerformance

    override fun mutateGen(n: Int) = specimen.mutateGen(n)

    override val gens: Array<Any>
        get() = specimen.gens

}