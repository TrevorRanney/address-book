
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

    val inputFile = File(inputFileName)
    val outputFile = File(outputFileName)

    if (!inputFile.exists()) {
        println("Input file does not exist")
        println(inputFile.absolutePath)
        return
    }

    val xmlMapper = XmlMapper(XmlFactory())
    xmlMapper.enable(SerializationFeature.INDENT_OUTPUT)
    val jsonMapper = ObjectMapper()

    if(inputFile.extension == "xml" && outputFile.extension == "json"){
        // Jackson does not seem to read the root, so I fixed that by wrapping it in another root for it to skip :,(
        // https://stackoverflow.com/questions/36317162/get-jackson-xmlmapper-to-read-root-element-name
        val xmlString = "<tag>" + inputFile.readText() + "</tag>"
        val jsonNode = jsonMapper.valueToTree<JsonNode>(xmlMapper.readTree(xmlString))
        outputFile.writeText(jsonMapper.writeValueAsString(jsonNode))
    }
    else if(inputFile.extension == "json" && outputFile.extension == "xml"){
        val jsonString = inputFile.readText()
        val tree = jsonMapper.readTree(jsonString)
        if(jsonString.startsWith('[')){
            val jsonWithRoot = jsonMapper.createObjectNode()
            jsonWithRoot.set<JsonNode>("Contacts", tree)
            val xmlString = xmlMapper.writeValueAsString(jsonWithRoot)
            outputFile.writeText(xmlString)
        }
        else{
            val xmlString = xmlMapper.writeValueAsString(tree)
            //The substring removes the <ObjectNode>; this could probably be done with a custom mapper
           outputFile.writeText(xmlString.substring(16, xmlString.length-16))
        }
    }
    else {
        println("input file must be xml and output file json, or input file must be json and output xml")
    }

    println("Converted $inputFileName to $outputFileName")
}