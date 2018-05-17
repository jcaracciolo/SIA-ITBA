package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.characters.Character
import ar.edu.itba.sia.utils.floor

class MixAndMatchReplacer(val amount: Int?, val selector: Selector): Replacer {

    override fun replace(parents: List<Character>, children: List<Character>): List<Character> {
        val amountThisTime = amount ?: (Math.random() * parents.size).floor()

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}