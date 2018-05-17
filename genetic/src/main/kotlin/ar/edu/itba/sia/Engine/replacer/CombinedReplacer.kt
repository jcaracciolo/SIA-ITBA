package ar.edu.itba.sia.Engine.replacer

import ar.edu.itba.sia.characters.Character
import ar.edu.itba.sia.utils.floor

class CombinedReplacer(val percentage: Double, val replacer1: Replacer, val replacer2: Replacer): Replacer {

    override fun replace(parents: List<Character>, children: List<Character>): List<Character> {
        val firstList = replacer1.replace(parents, children)
        val secondList = replacer2.replace(parents, children)

        val firstBatch = (percentage * parents.size).floor()
        val secondBatch = parents.size - firstBatch
        return firstList.take(firstBatch).plus(secondList.take(secondBatch))
    }
}