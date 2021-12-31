import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (A, B) = br.readLine()!!.split(" ").map { it.toLong() }
    var count = -1L
    val bfsQueue: Queue<List<Long>> = LinkedList() // 숫자, 카운트

    bfsQueue.offer(listOf(A, 1))
    while (bfsQueue.isNotEmpty()) {
        var (_A, _count) = bfsQueue.poll()
        if (_A == B) {
            count = _count
            break
        }
        _count++
        if (_A * 2 <= B) {
            bfsQueue.offer(listOf(_A * 2, _count))
        }
        _A = "${_A}1".toLong()
        if (_A <= B) {
            bfsQueue.offer(listOf(_A, _count))
        }
    }

    bw.write("$count")

    br.close()
    bw.flush()
    bw.close()
}
