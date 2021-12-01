import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val deque = ArrayDeque<Int>()
    List(br.readLine().toInt()) { br.readLine() }.forEach { command ->
        if (command.contains("push")) {
            val number = command.split(" ")[1].toInt()
            deque.addFirst(number)
        } else when (command) {
            "pop" -> {
                println(deque.removeFirstOrNull() ?: "-1")
            }
            "size" -> {
                println(deque.size)
            }
            "empty" -> {
                println(if (deque.isEmpty()) 1 else 0)
            }
            "top" -> {
                println(deque.firstOrNull() ?: -1)
            }
        }
    }
}
