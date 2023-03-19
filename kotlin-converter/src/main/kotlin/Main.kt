
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.io.File

fun main(args: Array<String>) {

    val inputFileName: String
    val outputFileName: String

    if (args.size != 2) {
        println("Usage: convert <input-file> <output-file>")
    }
    if(args.isEmpty()){
        inputFileName = "../ab.xml"
        outputFileName = "../ab.json"
        println("No files given using default: $inputFileName $outputFileName")
    }
    else if(args.size == 2){
        inputFileName = args[0]
        outputFileName = args[1]
    }
    else{
        println("Invalid Args")
        return
    }

    if (!File(inputFileName).exists()) {
        println("Input file not found: $inputFileName")
        return
    }

    convertFile(inputFileName, outputFileName)
}

fun convertFile(inputFileName: String, outputFileName: String) {
    val inputFile = File(inputFileName)
    val outputFile = File(outputFileName)

    val fileText = inputFile.readText()
    val convertedText: String?
    if(inputFile.extension == "xml" && outputFile.extension == "json"){
        convertedText = convertXmlToJson(fileText)
    }
    else if(inputFile.extension == "json" && outputFile.extension == "xml"){
        convertedText = convertJsonToXml(fileText)
    }
    else {
        convertedText = null
        println("input file must be xml and output file json, or input file must be json and output xml")
    }

    if(convertedText != null){
        outputFile.writeText(convertedText)
        println("Converted $inputFileName to $outputFileName")
    }
}

fun convertXmlToJson(fileText: String): String? {
    val xmlMapper = XmlMapper(XmlFactory())
    val jsonMapper = ObjectMapper()
    // Jackson does not seem to read the root, so I fixed that by wrapping it in another root for it to skip :,(
    // https://stackoverflow.com/questions/36317162/get-jackson-xmlmapper-to-read-root-element-name
    val xmlString = "<tag>$fileText</tag>"
    return try {
        val jsonNode = jsonMapper.valueToTree<JsonNode>(xmlMapper.readTree(xmlString))
        jsonMapper.writeValueAsString(jsonNode)
    } catch (e: JsonParseException) {
        println("Invalid Schema")
        null
    }
}

fun convertJsonToXml(fileText: String): String? {
    val xmlMapper = XmlMapper(XmlFactory())
    xmlMapper.enable(SerializationFeature.INDENT_OUTPUT)
    val jsonMapper = ObjectMapper()
    val tree: JsonNode
    try {
        tree = jsonMapper.readTree(fileText)
    } catch (e: JsonParseException) {
        println("Invalid Schema")
        return null
    }
    return if(fileText.startsWith('[')){
        val jsonWithRoot = jsonMapper.createObjectNode()
        jsonWithRoot.set<JsonNode>("Contacts", tree)
        xmlMapper.writeValueAsString(jsonWithRoot)
    }
    else{
        val xmlString = xmlMapper.writeValueAsString(tree)
        //The substring removes the <ObjectNode>; this could probably be done with a custom mapper
        xmlString.substring(16, xmlString.length-16)
    }
}