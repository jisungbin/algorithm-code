import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val OpenBracket = "("
const val CloseBracket = ")"

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val brackets = br.readLine()!!.split("").filterNot { it.isBlank() }

    var openBracketCount = 0
    var closeBracketCount = 0
    var addedBracketsCount = 0

    brackets.forEach { bracket ->
        if (bracket == OpenBracket) openBracketCount++
        else closeBracketCount++
        if (bracket == CloseBracket) {
            if (openBracketCount >= closeBracketCount) {
                openBracketCount--
                closeBracketCount--
            } else {
                closeBracketCount--
                addedBracketsCount++
            }
        }
    }

    addedBracketsCount += openBracketCount
    bw.write("$addedBracketsCount")
    br.close()
    bw.flush()
    bw.close()
}
