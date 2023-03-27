
import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ShuffleTest {

    @Test
    fun testCommandLine() {
        val outputFile = File("output.txt")
        outputFile.delete()
        
        main(arrayOf("TOURNAMENT", "DINNER", "DINTOUR"))
        assertEquals("CORRECT", outputFile.readText().trim())
    }

    @Test
    fun testFileInput() {
        val inputFile = File("input.txt")
        inputFile.writeText("cat bat cbaatt\n")
        inputFile.appendText("car bar cbarar\n")
        inputFile.appendText("rat tat tatart\n")
        inputFile.appendText("TOURNAMENT DINNER DINTOUR\n")

        val outputFile = File("output.txt")
        outputFile.delete()

        main(arrayOf("input.txt"))

        val newLine = System.getProperty("line.separator")
        assertEquals("CORRECT"+newLine+"CORRECT"+newLine+"INCORRECT"+newLine+"CORRECT"+newLine, outputFile.readText())
    }

    @Test
    fun testShuffles() {
        assertEquals("INCORRECT", isLegitimateShuffle("TOURNAMENT", "RANGE", "OURA"))
        assertEquals("INCORRECT", isLegitimateShuffle("TOURNAMENT", "RANGE", "TOURA"))
        assertEquals("INCORRECT", isLegitimateShuffle("TOURNAMENT", "RANGE", "TOURANGE"))
        assertEquals("INCORRECT", isLegitimateShuffle("rat", "tat", "tatart"))

        assertEquals("CORRECT", isLegitimateShuffle("CAT", "BAT", "CBAATT"))
        assertEquals("CORRECT", isLegitimateShuffle("car", "bar", "cbarar"))
        assertEquals("CORRECT", isLegitimateShuffle("TOURNAMENT", "GAME", "OURNAMEGAM"))
    }

    @Test
    fun testInvalidFile() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("test"))

        val newLine = System.getProperty("line.separator")
        assertEquals("Make sure input file exists", outputStream.toString().trim())
    }

    @Test
    fun testInvalidArgs() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("test","test"))

        val newLine = System.getProperty("line.separator")
        assertEquals("Please provide two original words and a shuffle as command line arguments. Or a file", outputStream.toString().trim())
    }
}
