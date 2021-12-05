import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    br.readLine()
    val scores = br.readLine()!!.split(" ").map { it.toDouble() }
    val maxScore = scores.maxOrNull()!!
    val avarage = scores.map { it / maxScore * 100 }.average()

    bw.write("$avarage\n")
    br.close()
    bw.flush()
    bw.close()
}
