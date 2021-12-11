import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val priorityQueue = PriorityQueue<Long> { o1, o2 ->
        val o1Abs = abs(o1)
        val o2Abs = abs(o2)
        if (o1Abs != o2Abs) {
            o1Abs.compareTo(o2Abs)
        } else {
            o1.compareTo(o2)
        }
    }

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
