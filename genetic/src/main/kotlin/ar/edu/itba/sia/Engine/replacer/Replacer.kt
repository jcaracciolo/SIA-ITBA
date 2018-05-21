package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

interface Replacer {

    fun replace(parents: List<Evolutionable>, children: List<Evolutionable>): List<Evolutionable>

}