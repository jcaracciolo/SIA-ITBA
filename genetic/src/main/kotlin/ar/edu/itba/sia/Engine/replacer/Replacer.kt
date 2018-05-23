package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.evolutionable.Evolutionable

interface Replacer {

    fun parentsToCross(): List<Evolutionable>

    fun replace(parents: List<Evolutionable>, children: List<Evolutionable>): List<Evolutionable>

}