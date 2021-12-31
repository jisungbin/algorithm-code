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
    val bfsQueue: Queue<Int> = LinkedList()
    val visitTimeAndCount = List(MAX) { mutableListOf(-1, 0) }

    bfsQueue.offer(me)
    visitTimeAndCount[me][0] = 0 // 방문 시간
    visitTimeAndCount[me][1] = 1 // 방문 가능한 방법의 수

    // sister 에 방문했을 때 멈추는게 아닌, 모든 곳에 다 방문하고 기록함
    while (bfsQueue.isNotEmpty()) {
        val _me = bfsQueue.poll()
        val meVisitTimeAndCount = visitTimeAndCount[_me]
        for (next in listOf(_me * 2, _me + 1, _me - 1)) {
            if (next in 0 until MAX) {
                if (visitTimeAndCount[next][0] == -1) { // 만약 방문한 적이 없다면
                    bfsQueue.offer(next)
                    visitTimeAndCount[next][0] = meVisitTimeAndCount[0] + 1
                    visitTimeAndCount[next][1] = meVisitTimeAndCount[1]
                } else if (visitTimeAndCount[next][0] == meVisitTimeAndCount[0] + 1) { // 만약 방문한 적이 있다면
                    visitTimeAndCount[next][1] += meVisitTimeAndCount[1] // 방문 가능한 방법의 수 업데이트
                }
            }
        }
    }

    bw.write(visitTimeAndCount[sister].joinToString("\n"))

    br.close()
    bw.flush()
    bw.close()
}
