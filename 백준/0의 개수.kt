import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val numbersCount = br.readLine()!!.toInt()
    repeat(numbersCount) {
        val (start, end) = br.readLine().split(" ").map { it.toInt() }
        val count = (start..end).sumOf { it.toString().count { char -> char == '0' } }
        bw.write("$count\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
