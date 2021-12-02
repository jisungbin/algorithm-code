import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    List(br.readLine().toInt()) { br.readLine() }.forEach { message ->
        val result = if (message.startsWith("(") && message.endsWith(")")) {
            var openPCount = 0
            var `return` = false
            message.forEach message@{ char ->
                if (`return`) return@message
                if (char == '(') {
                    openPCount++
                } else {
                    if (--openPCount == -1) `return` = true
                }
            }
            if (openPCount == 0) {
                "YES"
            } else {
                "NO"
            }
        } else {
            "NO"
        }
        println(result)
    }
}
