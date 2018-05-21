package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

class MultiGenMutator<G> (val numberOfGens: Int): GenMutator<G> {

    override fun mutate(specimen: Evolutionable<G>) {

        val indexes = (0 until specimen.gens.size).toList().shuffled()
        for (index in 0 until numberOfGens ){
            specimen.mutateGen(indexes[index])
        }
    }

}