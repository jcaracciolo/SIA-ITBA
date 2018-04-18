import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestTheTest{
    private val string = "hola"

    @Test
    fun initTest() {
        Assertions.assertEquals("hola", string)
    }
}