import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

class XmlJsonConverterTest {

    @Test
    fun testXmlToJson() {
        val xmlInputFile = File.createTempFile("test", ".xml")
        xmlInputFile.writeText("<AddressBook><Contacts><Contact><Name>John</Name><Email>john@example.com</Email></Contact></Contacts></AddressBook>")
        val jsonOutputFile = File.createTempFile("test", ".json")

        main(arrayOf(xmlInputFile.absolutePath, jsonOutputFile.absolutePath))

        assertEquals("{\"AddressBook\":{\"Contacts\":{\"Contact\":{\"Name\":\"John\",\"Email\":\"john@example.com\"}}}}", jsonOutputFile.readText())

        xmlInputFile.delete()
        jsonOutputFile.delete()
    }

    @Test
    fun testJsonToXml() {
        val jsonInputFile = File.createTempFile("test", ".json")
        jsonInputFile.writeText("{\"AddressBook\":{\"Contacts\":{\"Contact\":{\"Name\":\"John\",\"Email\":\"john@example.com\"}}}}")
        val xmlOutputFile = File.createTempFile("test", ".xml")

        main(arrayOf(jsonInputFile.absolutePath, xmlOutputFile.absolutePath))

        assertEquals("<AddressBook><Contacts><Contact><Name>John</Name><Email>john@example.com</Email></Contact></Contacts></AddressBook>", xmlOutputFile.readText().replace("\\s".toRegex(), ""))

        jsonInputFile.delete()
        xmlOutputFile.delete()
    }

    @Test
    fun testInvalidArgs() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("test"))

        val newLine = System.getProperty("line.separator")
        assertEquals("Usage: convert <input-file> <output-file>"+newLine+"Invalid Args", outputStream.toString().trim())
    }

    @Test
    fun testISourceFileDoesNotExist() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("test.xml", "text.json"))

        val newLine = System.getProperty("line.separator")
        assertEquals("Input file not found: test.xml", outputStream.toString().substringBefore(newLine))
    }

    @Test
    fun testInvalidXmlSchema() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val xmlInputFile = File.createTempFile("test", ".xml")
        xmlInputFile.writeText("<AddressBook><Contacts><Contact><Name>John</Name><Email>john@example.com</Email>")
        val jsonOutputFile = File.createTempFile("test", ".json")

        main(arrayOf(xmlInputFile.absolutePath, jsonOutputFile.absolutePath))

        assertEquals("Invalid Schema", outputStream.toString().trim())

        xmlInputFile.delete()
        jsonOutputFile.delete()
    }

    @Test
    fun testInvalidJsonSchema() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val jsonInputFile = File.createTempFile("test", ".json")
        jsonInputFile.writeText("{\"AddressBook\":{\"Contacts\":{:{\"Name\":\"John\",\"Email\":\"john@example.com\"}")
        val xmlOutputFile = File.createTempFile("test", ".xml")

        main(arrayOf(jsonInputFile.absolutePath, xmlOutputFile.absolutePath))

        assertEquals("Invalid Schema", outputStream.toString().trim())

        jsonInputFile.delete()
        xmlOutputFile.delete()
    }
}
