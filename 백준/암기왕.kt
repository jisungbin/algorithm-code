import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val caseCount = br.readLine()!!.toInt()
    repeat(caseCount) {
        val numbersMap = mutableMapOf<String, Int>()
        val note2ContainsNumbers = mutableListOf<Int>()
        val noteCount = br.readLine()!!.toInt()
        val noteNumbers = br.readLine()!!.split(" ")
        for (i in 0 until noteCount) {
            numbersMap[noteNumbers[i]] = 0
        }
        val note2Count = br.readLine()!!.toInt()
        val note2Numbers = br.readLine()!!.split(" ")
        for (i in 0 until note2Count) {
            note2ContainsNumbers.add(
                if (numbersMap[note2Numbers[i]] != null) {
                    1
                } else {
                    0
                }
            )
        }
        bw.write("${note2ContainsNumbers.joinToString("\n")}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
