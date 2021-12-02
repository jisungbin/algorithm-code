import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val deque = ArrayDeque((1..(br.readLine()!!.toInt())).toList())
    while (deque.size != 1) {
        deque.removeFirst()
        deque.addLast(deque.removeFirst())
    }
    bw.write("${deque.first()}\n")
    br.close()
    bw.flush()
    bw.close()
}
