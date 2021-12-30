import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (a, b) = br.readLine()!!.split(" ").map { it.toInt() }
    if (a > b) {
        bw.write(">")
    } else if (a < b) {
        bw.write("<")
    } else {
        bw.write("==")
    }

    br.close()
    bw.flush()
    bw.close()
}
