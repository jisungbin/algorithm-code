import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val priorityQueue = PriorityQueue<Long>(Collections.reverseOrder())

    val numberCount = br.readLine()!!.toInt()
    repeat(numberCount) {
        val number = br.readLine()!!.toLong()
        if (number == 0L) {
            bw.write("${priorityQueue.poll() ?: 0}\n")
        } else {
            priorityQueue.offer(number)
        }
    }

    br.close()
    bw.flush()
    bw.close()
}
