import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val deque = ArrayDeque<Int>()
    List(br.readLine().toInt()) { br.readLine() }.forEach { command ->
        if (command.contains("push")) {
            val number = command.split(" ")[1].toInt()
            deque.addLast(number)
        } else when (command) {
            "pop" -> {
                bw.write("${deque.removeFirstOrNull() ?: "-1"}\n")
            }
            "size" -> {
                bw.write("${deque.size}\n")
            }
            "empty" -> {
                bw.write("${if (deque.isEmpty()) 1 else 0}\n")
            }
            "front" -> {
                bw.write("${deque.firstOrNull() ?: "-1"}\n")
            }
            "back" -> {
                bw.write("${deque.lastOrNull() ?: "-1"}\n")
            }
        }
    }

    br.close()
    bw.flush()
    bw.close()
}
