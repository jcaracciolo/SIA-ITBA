package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.evolutionable.characters.Evolutionable

class OnlyChildrenReplacer(val selector: Selector): Replacer {

    override fun replace(parents: List<Evolutionable>, children: List<Evolutionable>): List<Evolutionable> =
            selector.select(children, children.size)
}