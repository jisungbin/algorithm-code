import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val strings = StringBuilder()
    val temp = Stack<String>()

    var openBracket = false
    br.readLine().toList().map(Char::toString).forEach { char ->
        when (openBracket) {
            true -> {
                strings.append(char)
                if (char == ">") {
                    openBracket = false
                }
            }
            else -> {
                if (char == "<") {
                    while (temp.isNotEmpty()) {
                        strings.append(temp.pop())
                    }
                    strings.append(char)
                    openBracket = true
                } else {
                    if (char != " ") {
                        temp.add(char)
                    } else {
                        while (temp.isNotEmpty()) {
                            strings.append(temp.pop())
                        }
                        strings.append(char)
                    }
                }
            }
        }
    }

    while (temp.isNotEmpty()) {
        strings.append(temp.pop())
    }
    bw.write(strings.toString())

    br.close()
    bw.flush()
    bw.close()
}
