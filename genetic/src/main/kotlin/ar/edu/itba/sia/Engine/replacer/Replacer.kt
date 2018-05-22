package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.evolutionable.characters.Evolutionable

interface Replacer {
    val parentsNeeded: Int
    val parentsPassing: Int
    val childrenNeeded: Int
    fun <G> replace(parents: List<Evolutionable<G>>, children: List<Evolutionable<G>>): List<Evolutionable<G>>

}