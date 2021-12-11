import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val queue = PriorityQueue<Int>(Collections.reverseOrder())
    val graphCount = br.readLine()!!.toInt()
    repeat(graphCount) {
        queue.addAll(br.readLine()!!.split(" ").map { it.toInt() })
    }

    repeat(graphCount - 1) {
        queue.poll()
    }

    bw.write("${queue.peek()}")
    br.close()
    bw.flush()
    bw.close()
}
