import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val unstartLectures =
        List(br.readLine()!!.toInt()) { br.readLine()!!.split(" ").drop(1).map { it.toInt() } }.sortedBy { it.first() }
    val startLecturesEndTimePriorityQueue = PriorityQueue<Int>()
    var needLecturesCount = 0

    for (i in unstartLectures.indices) {
        // 강의들이 시작이 제일 빠른 순서로 정렬된 상태이므로, `startLecturesEndTimePriorityQueue.peek() <= unstartLectures[i].first()`
        // 부분으로 이 강의가 다음에 시작 될 수 딱 하나의 강의만 빼고 다 지워줌 (시간복잡도를 줄이기 위함)
        // 위 시간복잡도를 줄이기 위한 과정으로, 이전에 모든 강의들을 다 지워 버렸으므로
        // max 함수로 더 큰 강의실의 개수를 정답으로 가져옴.
        while (startLecturesEndTimePriorityQueue.isNotEmpty() && startLecturesEndTimePriorityQueue.peek() <= unstartLectures[i].first()) {
            startLecturesEndTimePriorityQueue.poll()
        }
        startLecturesEndTimePriorityQueue.add(unstartLectures[i].last())
        needLecturesCount = max(needLecturesCount, startLecturesEndTimePriorityQueue.size)
    }

    bw.write("$needLecturesCount")
    br.close()
    bw.flush()
    bw.close()
}
