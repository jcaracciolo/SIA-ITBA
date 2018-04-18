import chainReaction.utils.CRParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ParserTest{
    private val inputPath = "./src/test/resources/boardTest"

    @Test
    fun initTest() {
//    print(System.getProperty("user.dir"))
        val game = CRParser.parseBoard(inputPath)
        if(game != null){
            Assertions.assertEquals(2, game.board.maxColor)
        }
    }
}