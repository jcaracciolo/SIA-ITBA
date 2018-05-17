package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.characters.Character

class OnlyChildrenReplacer(val selector: Selector): Replacer {

    override fun replace(parents: List<Character>, children: List<Character>): List<Character> =
            selector.select(children, children.size)
}