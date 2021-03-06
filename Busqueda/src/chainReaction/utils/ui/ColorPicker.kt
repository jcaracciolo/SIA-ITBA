package chainReaction.utils.ui

import javafx.scene.paint.Color

class ColorPicker {
    companion object {
        private val colors : Array<Color>

        init{
            colors = Array<Color>(25) { index ->
                return@Array when(index){
                    0 -> Color.GREY
                    1 -> Color.BLUE
                    2 -> Color.GREEN
                    3 -> Color.DARKMAGENTA
                    4 -> Color.ORANGE
                    6 -> Color.PINK
                    5 -> Color.LIGHTBLUE
                    7 -> Color.CORAL
                    8 -> Color.VIOLET
                    9 -> Color.AQUA
                    10 -> Color.BEIGE
                    11 -> Color.BLUEVIOLET
                    12 -> Color.YELLOW
                    13 -> Color.DARKORANGE
                    14 -> Color.DARKGREEN
                    15 -> Color.DARKRED
                    16 -> Color.CRIMSON
                    17 -> Color.CYAN
                    18 -> Color.LIME
                    19 -> Color.LINEN
                    20 -> Color.MAGENTA
                    21 -> Color.BROWN
                    22 -> Color.DEEPPINK
                    23 -> Color.SALMON
                    24 -> Color.GOLD
                    25 -> Color.SILVER
                    else -> Color.WHITE
                }
            }

        }

        fun getColor(n : Int): Color = colors[n]
    }
}