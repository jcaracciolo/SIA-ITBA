package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.utils.floor

class CombinedReplacer(val percentage: Double, val replacer1: Replacer, val replacer2: Replacer): Replacer {

    override fun parentsToCross(): List<Evolutionable> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replace(parents: List<Evolutionable>, children: List<Evolutionable>): List<Evolutionable> {
        val firstList = replacer1.replace(parents, children)
        val secondList = replacer2.replace(parents, children)

        val firstBatch = (percentage * parents.size).floor()
        val secondBatch = parents.size - firstBatch
        return firstList.take(firstBatch).plus(secondList.take(secondBatch))
    }

}