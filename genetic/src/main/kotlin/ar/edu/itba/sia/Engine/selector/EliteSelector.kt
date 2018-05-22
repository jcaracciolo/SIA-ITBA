package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.characters.Evolutionable
import kotlin.math.E

class EliteSelector: Selector {

    override fun <G> select(generation: List<Evolutionable<G>>, amount: Int): List<Evolutionable<G>> {
        var selectedSpecimens = ArrayList<Evolutionable<G>>()

        Comparator
        generation.sortedWith(Comparator(function = (first: Evolutionable<G>, second: Evolutionable<G> -> Int {
            when{

            }
        }))

        for (index in 0 until amount) {
            selectedSpecimens.add(generation[index])
        }

        return selectedSpecimens
    }
}