package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.utils.floor

class MixAndMatchReplacer(val amount: Int?, val selector: Selector): Replacer {


    override fun <G> parentsToCross(selector: Selector): List<Evolutionable<G>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <G> replace(parents: List<Evolutionable<G>>, children: List<Evolutionable<G>>): List<Evolutionable<G>> {
        val amountThisTime = amount ?: (Math.random() * parents.size).floor()

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}