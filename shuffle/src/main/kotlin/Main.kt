import java.io.File

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
    var i = 0
    var j = 0
    for (k in shuffle.indices) {
        if (i < word1.length && shuffle[k] == word1[i]) {
            i++
        } else if (j < word2.length && shuffle[k] == word2[j]) {
            j++
        } else {
            return "INCORRECT"
        }
    }
    return "CORRECT"
}