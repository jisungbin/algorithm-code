import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val deque = ArrayDeque<Int>()
    List(br.readLine().toInt()) { br.readLine() }.forEach { command ->
        if (command.contains("push")) {
            val number = command.split(" ")[1].toInt()
            deque.add(number)
        } else when (command) {
            "pop" -> {
                bw.write("${deque.poll() ?: -1}\n")
            }
            "size" -> {
                bw.write("${deque.size}\n")
            }
            "empty" -> {
                bw.write("${if (deque.isEmpty()) 1 else 0}\n")
            }
            "front" -> {
                bw.write("${deque.peek() ?: -1}\n")
            }
            "back" -> {
                bw.write("${deque.peekLast() ?: -1}\n")
            }
        }
    }
    bw.flush()
}
