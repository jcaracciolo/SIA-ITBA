package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.Engine.selector.Selector
import ar.edu.itba.sia.evolutionable.Evolutionable
import ar.edu.itba.sia.utils.floor

class UniformReplacer(val amount: Int?, val selector: Selector): Replacer {

    var amountThisTime: Int = 0

    override fun parentsToCross(parents: List<Evolutionable>): List<Evolutionable> {
        amountThisTime = amount ?: (Math.random() * parents.size -1 ).floor() + 1
        return parents.sortedByDescending { it.getPerformance() }

    }

    override fun replace(parents: List<Evolutionable>, children: List<Evolutionable>): List<Evolutionable> =
            selector.select(parents.plus(children), parents.size)

}