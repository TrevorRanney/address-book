import java.io.File
import java.lang.Math.min

fun main(args: Array<String>) {
    if (args.size == 3) {
        val word1 = args[0]
        val word2 = args[1]
        val shuffle = args[2]
        val result = isLegitimateShuffle(word1, word2, shuffle)
        println(result)
        writeOutputFile(result)
    }
    else if(args.size == 1){
            val file = File(args[0])
            if(file.exists()){
                val results = checkFileForShuffle(file)
                writeOutputFile(results)
            }
            else{
                println("Make sure input file exists")
            }
    }
    else{
        println("Please provide two original words and a shuffle as command line arguments. Or a file")
    }
}

fun checkFileForShuffle(inputFile: File): String {
    val results = buildString {
        inputFile.forEachLine { line ->
            val words = line.split(" ")
            val result = isLegitimateShuffle(words[0], words[1], words[2])
            val newLine = System.getProperty("line.separator")
            println(result)
            append(result+newLine)
        }
    }
    return results
}

fun writeOutputFile(results: String){
    val outputFile = File("output.txt")
    outputFile.writeText(results)
    println("Results available in output.txt")
}


fun isLegitimateShuffle(word1: String, word2: String, shuffle: String): String {
    var word1Position = 0
    var word2Position = 0

    if(shuffle[0] != word1[0] &&
        shuffle[0] != word2[0]){
        val possibleWord1StartPosition = word1.indexOf(shuffle[0])
        val possibleWord2StartPosition = word1.indexOf(shuffle[0])
        if(possibleWord1StartPosition != -1){
            word1Position = possibleWord1StartPosition
        }
        else if(possibleWord2StartPosition != -1){
            word2Position = possibleWord2StartPosition
        }
    }

    for (shufflePosition in shuffle.indices) {
        println(word1[min(word1Position, word1.length-1)] + " " + word2[min(word2Position, word2.length-1)] + " " + shuffle[min(shufflePosition, shuffle.length-1)])
        if (word1Position < word1.length && shuffle[shufflePosition] == word1[word1Position]) {
            word1Position++
        } else if (word2Position < word2.length && shuffle[shufflePosition] == word2[word2Position]) {
            word2Position++
        } else {
            return "INCORRECT"
        }
    }
    return "CORRECT"
}