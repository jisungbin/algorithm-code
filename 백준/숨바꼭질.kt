import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

private const val MAX = 100001

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (me, sister) = br.readLine()!!.split(" ").map { it.toInt() }
    var count = 0
    val visWithCount = MutableList(MAX) { 0 }
    val bfsQueue: Queue<Int> = LinkedList()

    fun bfs() {
        visWithCount[me] = 1
        bfsQueue.offer(me)
        var `break` = false
        while (bfsQueue.isNotEmpty()) {
            if (`break`) break
            val _me = bfsQueue.poll()
            repeat(3) {
                var next = _me
                if (`break`) return@repeat
                when (it) {
                    0 -> {
                        next++
                    }
                    1 -> {
                        next--
                    }
                    2 -> {
                        next *= 2
                    }
                }

                if (next == sister) {
                    count = visWithCount[_me]
                    `break` = true
                } else if (next in 0 until MAX && visWithCount[next] == 0) {
                    bfsQueue.offer(next)
                    visWithCount[next] = visWithCount[_me] + 1
                }
            }
        }
    }

    if (me != sister) {
        bfs()
    }
    bw.write("$count")

    br.close()
    bw.flush()
    bw.close()
}
