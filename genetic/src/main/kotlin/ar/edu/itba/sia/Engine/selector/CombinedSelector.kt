package ar.edu.itba.sia.Engine.selector

import ar.edu.itba.sia.characters.Character
import ar.edu.itba.sia.utils.floor
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class CombinedSelector(
        val percentage: Double,
        val selector1: Selector,
        val selector2: Selector): Selector {

    override fun select(generation: List<Character>, amount: Int): List<Character> {
        val list = ArrayList<Character>()

        val firstBatch = (percentage*amount).roundToInt()
        val secondBatch = amount - firstBatch

        list.addAll(selector1.select(generation, firstBatch))
        list.addAll(selector2.select(generation, secondBatch))
        return list
    }
}