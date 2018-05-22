package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.evolutionable.Evolutionable

class OnlyChildrenReplacer(val selector: Selector): Replacer {

    override fun <G> parentsToCross(): List<Evolutionable<G>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <G> replace(parents: List<Evolutionable<G>>, children: List<Evolutionable<G>>): List<Evolutionable<G>> =
            selector.select(children, children.size)

}