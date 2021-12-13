import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

private val queue = PriorityQueue<Int>()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val count = br.readLine()!!.toInt()
    repeat(count) {
        val number = br.readLine()!!.toInt()
        if (number == 0) {
            bw.write("${queue.poll() ?: 0}\n")
        } else {
            queue.add(number)
        }
    }

    br.close()
    bw.flush()
    bw.close()
}
