package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.utils.floor

class LessChildrenReplacer(val amount: Int?, val selector: Selector): Replacer {

    override fun parentsToCross(): List<Evolutionable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replace(parents: List<Evolutionable>, children: List<Evolutionable>): List<Evolutionable> {
        val amountThisTime = amount ?: (Math.random() * parents.size).floor()

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}