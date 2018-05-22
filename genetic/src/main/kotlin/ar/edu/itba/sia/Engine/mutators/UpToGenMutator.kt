package ar.edu.itba.sia.Engine.mutators

import ar.edu.itba.sia.evolutionable.Evolutionable
import java.util.*

class UpToGenMutator(val numberOfGens: Int): GenMutator {


    override fun <G> mutate(specimen: Evolutionable<G>) {
        val gensToMutate = Random().nextInt(numberOfGens)

        val indexes = (0 until specimen.gens.size).toList().shuffled()
        for (index in 0 until gensToMutate ){
            specimen.mutateGen(indexes[index])
        }
    }

}