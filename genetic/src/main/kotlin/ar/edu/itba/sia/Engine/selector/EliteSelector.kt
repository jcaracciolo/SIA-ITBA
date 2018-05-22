package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.Evolutionable

class EliteSelector: Selector {

    override fun <G> select(generation: List<Evolutionable<G>>, amount: Int): List<Evolutionable<G>> {
        var selectedSpecimens = ArrayList<Evolutionable<G>>()



        for (index in 0 until amount) {
            selectedSpecimens.add(generation[index])
        }

        return selectedSpecimens
    }
}