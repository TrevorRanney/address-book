import java.io.File

fun main(args: Array<String>) {

    val outputFile = File("output.txt")

    if (args.size == 3) {
        val word1 = args[0]
        val word2 = args[1]
        val shuffle = args[2]
        val result = isLegitimateShuffle(word1, word2, shuffle)
        println(result)
        outputFile.writeText(result)
        println("Results available in output.txt")
    }
    else if(args.size == 1){
            val file = File(args[0])
            if(file.exists()){
                outputFile.writeText("")
                file.forEachLine { line ->
                    val words = line.split(" ")
                    val result = isLegitimateShuffle(words[0], words[1], words[2])
                    val newLine = System.getProperty("line.separator")
                    println(result)
                    outputFile.appendText(result+newLine)
                }
                println("Results available in output.txt")
            }
            else{
                println("Make sure input file exists")
            }
    }
    else{
        println("Please provide two original words and a shuffle as command line arguments. Or a file")
    }

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