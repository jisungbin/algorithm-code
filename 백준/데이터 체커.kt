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
        if (o1.first != o2.first) o1.first - o2.first
        else o1.second - o2.second
    }.toMutableList()

    var newCircle: Pair<Int, Int>
    var lastCircle = circles[0]
    var isCircleNonOverlap = "YES"

    for (i in 1 until circles.size) {
        newCircle = circles[i]
        if (lastCircle.second >= newCircle.first && lastCircle.second <= newCircle.second) {
            isCircleNonOverlap = "NO"
            break
        }
        lastCircle = newCircle
    }

    bw.write(isCircleNonOverlap)
    br.close()
    bw.flush()
    bw.close()
}
