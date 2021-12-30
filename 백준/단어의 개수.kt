import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val strings = br.readLine()!!.split(" ").filterNot { it.isBlank() }
    bw.write("${strings.count()}")

    br.close()
    bw.flush()
    bw.close()
}
