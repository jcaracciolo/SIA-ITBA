package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.evolutionable.characters.Evolutionable
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class CombinedSelector(
        val percentage: Double,
        val selector1: Selector,
        val selector2: Selector): Selector {


    override fun <G> select(generation: List<Evolutionable<G>>, amount: Int): List<Evolutionable<G>> {
        val list = ArrayList<Evolutionable<G>>()

        val firstBatch = (percentage*amount).roundToInt()
        val secondBatch = amount - firstBatch

        list.addAll(selector1.select(generation, firstBatch))
        list.addAll(selector2.select(generation, secondBatch))
        return list
    }
}