package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.Evolutionable

class MultiGenMutator (val numberOfGens: Int): GenMutator {

    override fun mutate(specimen: Evolutionable) {
        val indexes = (0 until specimen.gens.size).toList().shuffled()
        for (index in 0 until numberOfGens ){
            specimen.mutateGen(indexes[index])
        }
    }

}