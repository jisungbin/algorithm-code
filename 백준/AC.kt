import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val caseCount = br.readLine()!!.toInt()

    repeat(caseCount) {
        val commands = br.readLine()!!.split("").drop(1).dropLast(1)
        br.readLine()!!
        val numbers =
            ArrayDeque(br.readLine()!!.drop(1).dropLast(1).split(",").filterNot { it.isBlank() })
        var isReversed = false
        var isError = false
        for (i in commands.indices) {
            if (isError) break
            when (commands[i]) {
                "R" -> isReversed = !isReversed
                "D" -> {
                    if (numbers.isEmpty()) {
                        isError = true
                    } else {
                        if (isReversed) {
                            numbers.removeLast()
                        } else {
                            numbers.removeFirst()
                        }
                    }
                }
            }
        }
        if (isReversed) {
            numbers.reverse()
        }
        bw.write("${if (isError) "error" else numbers.joinToString(separator = ",", prefix = "[", postfix = "]")}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
