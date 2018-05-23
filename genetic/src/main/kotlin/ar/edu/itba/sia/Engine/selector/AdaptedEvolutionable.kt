package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class AdaptedEvolutionable<G> (val newPerformance: Double, val specimen: Evolutionable<G>) : Evolutionable<G> {
    override fun getDescendant(): Evolutionable<G> = specimen.getDescendant()

    override fun getPerformance(): Double = newPerformance

    override fun mutateGen(n: Int) = specimen.mutateGen(n)

    override val gens: Array<G>
        get() = specimen.gens

}