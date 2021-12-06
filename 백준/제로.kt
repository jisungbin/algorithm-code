import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val deque = ArrayDeque<Int>()
    List(br.readLine().toInt()) { br.readLine()!!.toInt() }.forEach { number ->
        if (number == 0) {
            deque.removeLastOrNull()
        } else {
            deque.addLast(number)
        }
    }

    bw.write("${deque.sum()}\n")
    br.close()
    bw.flush()
    bw.close()
}
