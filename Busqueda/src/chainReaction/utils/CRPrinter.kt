//package chainReaction.utils
//
//import chainReaction.game.CRState
//import jdk.nashorn.internal.objects.NativeString.substr
//import java.util.*
//
//class CRPrinter {
//
//    companion object {
//        private data class RGB(val r: Int,val g: Int, val b: Int)
//
//        val ANSI_RESET = "\u001B[0m"
//        private var colors = Array(0) { RGB(0,0,0) }
//        private var shapes = Array(0) { Character('A')}
//        private var initialized = false
//
//        fun printState(state: CRState?) {
//            if(!initialized) {
//
//            }
//            var s="/\\/\\/\\/\\/\\";
//            s=s+" "+s+" "+ s+" "+ s+" "+ s+" "+ s+" "+ s+" "+ s;
//            for (colnum in 0..77) {
//                var r = 255-(colnum*255/76);
//                var g = (colnum*510/76);
//                var b = (colnum*255/76);
//                if (g>255) g = 510-g;
//                print("\u001b[48;2;"+r+";"+g+";"+b+"m")
//                print("\u001b[38;2;"+(255-r)+";"+(255-g)+";"+(255-b)+"m");
//                print(substr(s,colnum+1,1)+ "\u001b[0m")
//            }
//        }
//
//        private fun initialize(state: CRState) {
//            val game = state.game
//            val maxColors = game.board.maxColor
//            val maxShapes = game.board.maxShape
//
//            val rand = Random()
//            colors = Array(maxColors) {RGB(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255))}
//            shapes = Array(maxShapes) { 'A'}
//            for (i in 0..maxShapes) {
//                shapes[i] =
//            }
//
//
//
//        }
//
//        private fun generateChange(i: Int): String {
//            return "\u001b[38;2;40;177;249m"
////            return CHANGE_COLOR + "[" + (bg+i) + ";" + (fg+i) + "m"
//        }
//
//        private fun blackFont(): String {
//            return changeForeground(0,0,0)
//        }
//
//        private fun whiteFont(): String {
//            return changeForeground(255,255,255)
//        }
//
//        private fun changeForeground(r: Int, g: Int, b: Int): String {
//            return "\u001b[38;2;"+r+";"+g+";"+b+"m"
//        }
//
//        private fun changeBackground(r: Int, g: Int, b: Int): String {
//            return "\u001b[48;2;"+r+";"+g+";"+b+"m"
//        }
//
//    }
//}