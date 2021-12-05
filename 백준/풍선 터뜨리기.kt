import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val ballonsCount = br.readLine()!!.toInt()
    val ballons = ArrayDeque<Pair<Int, Int>>(ballonsCount)
    val sb = StringBuilder()
    val input = StringTokenizer(br.readLine()!!)
    var number = 0

    while (input.hasMoreTokens()) {
        val ballon = ++number to input.nextToken().toInt()
        ballons.add(ballon)
    }

    while (ballons.size != 1) {
        val ballon = ballons.first()
        val ballonNumber = ballon.first
        val ballonNextIndex = ballon.second
        sb.append("$ballonNumber ")

        if (ballonNextIndex > 0) {
            repeat(ballonNextIndex) {
                ballons.addLast(ballons.removeFirst())
            }
            ballons.remove(ballon)
        } else {
            ballons.remove(ballon)
            repeat(ballonNextIndex * -1) {
                ballons.addFirst(ballons.removeLast())
            }
        }
    }

    sb.append(ballons.first().first)
    bw.write("$sb")

    br.close()
    bw.flush()
    bw.close()
}
