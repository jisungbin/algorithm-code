import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val circlesCount = br.readLine()!!.toInt()
    val circles = List(circlesCount) {
        val (x, r) = br.readLine()!!.split(" ").map { it.toInt() }
        x - r to x + r
    }.sortedWith { o1, o2 ->
        // 같지 않는 값 찾아서 오름차순 정렬
        if (o1.first != o2.first) o1.first - o2.first
        else o1.second - o2.second
    }.toMutableList()

    var newCircle: Pair<Int, Int>
    var lastCircleEndPoint = circles[0].second
    var isCircleNonOverlap = "YES"

    for (i in 1 until circles.size) {
        newCircle = circles[i]
        // 마지막으로 그린 원의 끝 점이 새로 그릴 원의 시작점보다 밖에 있거나 같고,
        // 마지막으로 그린 원의 끝 점이 새로 그릴 원의 끝 점보다 작거나 같으면
        // (새로 그릴 원의 끝 점이 마지막으로 그린 원의 끝 점부터 크거나 같으면)
        // (만약 마지막으로 그린 원의 끝 점이 새로 그릴 원의 끝 점보다 크다면, 새로 그릴 원이 마지막으로 그린 원 안에 들어가서 겹점이 안 생김)
        // 새로 그릴 원의 시작 점이 마지막으로 그린 원 안에서 시작하고, 새로 그릴 원의 끝 점이 마지막으로 그린 원 바깥쪽에 그려지므로 겹점이 생김

        // 원이 시작 좌표로 오름차순으로 정렬이 돼서 1~~ 순서로 점점 시작 점이 커지므로,
        // 원 시작점 비교를 마지막으로 그린 원의 끝 점과 새로 그릴 원의 시작 점으로 비교를 해야 함
        if (lastCircleEndPoint >= newCircle.first && lastCircleEndPoint <= newCircle.second) {
            isCircleNonOverlap = "NO"
            break
        }
        lastCircleEndPoint = newCircle.second
    }

    bw.write(isCircleNonOverlap)
    br.close()
    bw.flush()
    bw.close()
}
