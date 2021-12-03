import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val input = br.readLine()!!.split("").filterNot { it.isBlank() }
    var line = 0
    var cuttedLine = 0

    input.forEachIndexed { index, char ->
        if (char == "(") {
            if (input[index + 1] != ")") { // 레이저가 아닐 때
                line++
            }
        } else { // ")"
            if (input[index - 1] == "(") { // 레이저일 때
                cuttedLine += line
            } else { // 철근이 닫혔을 때
                line--
                cuttedLine += 1
            }
        }
    }

    bw.write("$cuttedLine\n")
    br.close()
    bw.flush()
    bw.close()
}
