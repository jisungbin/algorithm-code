import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine()!!.toInt()
    for (it in 1..n) {
        repeat(n - it) {
            bw.write(" ")
        }
        repeat(it) {
            bw.write("*")
        }
        bw.write("\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
