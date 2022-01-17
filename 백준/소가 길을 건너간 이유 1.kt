import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private const val DefaultMapValue = -1

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine()!!.toInt()
    val cows = mutableMapOf<Int, Int>()
    var cowMoveCount = 0

    repeat(N) {
        val (cow, where) = br.readLine()!!.split(" ").map(String::toInt)
        val defaultWhere = cows[cow]
        if (defaultWhere == null) {
            cows[cow] = where
        } else {
            if (defaultWhere != where) {
                cowMoveCount++
                cows[cow] = where
            }
        }
    }

    bw.write("$cowMoveCount")

    br.close()
    bw.flush()
    bw.close()
}
