import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val year = br.readLine()!!.toInt()
    bw.write(if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) "1" else "0")

    br.close()
    bw.flush()
    bw.close()
}
