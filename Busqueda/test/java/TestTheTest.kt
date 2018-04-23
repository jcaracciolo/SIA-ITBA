import junit.framework.Assert.assertEquals
import org.junit.Test

class TestTheTest{
    private val string = "hola"

    @Test
    fun initTest() {
        assertEquals("hola", string)
    }
}