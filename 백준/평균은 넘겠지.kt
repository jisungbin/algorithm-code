import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.text.DecimalFormat
import kotlin.math.round

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var scores: List<Int>
    var avarage: Double
    var goodStudentsCount: Double
    var goodStudentsPercentage: String
    val goodStudentsPercentageFormat = DecimalFormat("0.000")

    repeat(br.readLine().toInt()) {
        scores = br.readLine()!!.split(" ").toMutableList().apply {
            removeFirst()
        }.map { it.toInt() }
        avarage = scores.average()
        goodStudentsCount = scores.count { it > avarage }.toDouble()
        goodStudentsPercentage =
            goodStudentsPercentageFormat.format(round(goodStudentsCount / scores.size * 100 * 1000) / 1000)

        bw.write("$goodStudentsPercentage%\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
