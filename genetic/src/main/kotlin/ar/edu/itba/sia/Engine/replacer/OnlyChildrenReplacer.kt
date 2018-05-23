package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.evolutionable.Evolutionable

class OnlyChildrenReplacer(val selector: Selector): Replacer {

    override fun parentsToCross(): List<Evolutionable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replace(parents: List<Evolutionable>, children: List<Evolutionable>): List<Evolutionable> =
            selector.select(children, children.size)

}