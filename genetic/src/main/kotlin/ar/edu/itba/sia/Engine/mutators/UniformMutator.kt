package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

class UniformMutator: Mutator {

    override fun <G> mutate(specimen: Evolutionable<G>, generation: Int): Evolutionable<G> {
        //TODO
        return specimen
    }

}