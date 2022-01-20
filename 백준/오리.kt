import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val input = br.readLine()!!.toList()
    val length = input.size

    if (length % 5 != 0) {
        bw.write("-1")
    } else {
        val vis = MutableList(length) { false }
        val cry = "quack".toList()
        var duckCount = 0

        fun getDuckCount(startIndex: Int) {
            var cryIndex = 0
            var firstTurn = true
            for (i in startIndex until length) {
                if (input[i] == cry[cryIndex] && !vis[i]) {
                    vis[i] = true
                    if (input[i] == 'k') {
                        if (firstTurn) {
                            duckCount++
                            firstTurn = false
                        }
                        cryIndex = 0
                        continue
                    }
                    cryIndex++
                }
            }
        }

        for (i in 0 until length) {
            if (input[i] == 'q' && !vis[i]) {
                getDuckCount(i)
            }
        }

        if (!vis.all { it } || duckCount == 0) {
            bw.write("-1")
        } else {
            bw.write("$duckCount")
        }
    }

    br.close()
    bw.flush()
    bw.close()
}
