import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val ballonsCount = br.readLine()!!.toInt()
    val ballons = ArrayDeque((1..ballonsCount).zip(br.readLine()!!.split(" ").map { it.toInt() }))
    val punchedBallons = StringBuffer()

    var ballon: Pair<Int, Int>
    var ballonNumber: Int
    var ballonNextIndex: Int

    while (true) {
        ballon = ballons.first()
        ballonNumber = ballon.first
        ballonNextIndex = ballon.second
        ballons.removeFirst()
        punchedBallons.append("$ballonNumber ")
        if (ballons.isEmpty()) break
        if (ballonNextIndex > 0) {
            repeat(ballonNextIndex - 1) {
                ballons.addLast(ballons.removeFirst())
            }
        } else {
            repeat(-ballonNextIndex) {
                ballons.addFirst(ballons.removeLast())
            }
        }
    }

    bw.write(punchedBallons.removeSuffix(" ").toString())

    br.close()
    bw.flush()
    bw.close()
}
