package ar.edu.itba.sia.Engine.crossOver

import ar.edu.itba.sia.evolutionable.Evolutionable
interface Crosser {
    fun crossOver(father: Evolutionable, mother: Evolutionable): List<Evolutionable>
}