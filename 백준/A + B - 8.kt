import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    repeat(br.readLine().toInt()) { index ->
        val (a, b) = br.readLine()!!.split(" ").map { it.toInt() }
        bw.write("Case #${index + 1}: $a + $b = ${a + b}\n")
    }
    br.close()
    bw.flush()
    bw.close()
}
