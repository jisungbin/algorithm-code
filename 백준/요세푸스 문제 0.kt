import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (length, step) = br.readLine()!!.split(" ").map { it.toInt() }
    val deque = ArrayDeque((1..length).toList())
    val pickedNumber = mutableListOf<Int>()

    while (deque.size > 1) {
        repeat(step - 1) {
            deque.addLast(deque.removeFirst())
        }
        pickedNumber.add(deque.removeFirst())
    }

    pickedNumber.add(deque.removeFirst())
    bw.write("${pickedNumber.joinToString(separator = ", ", prefix = "<", postfix = ">")}\n")
    br.close()
    bw.flush()
    bw.close()
}
