package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.characters.Evolutionable
interface Crosser {
    fun <G> crossOver(father: Evolutionable<G>, mother: Evolutionable<G>): List<Evolutionable<G>>
}