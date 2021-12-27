import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val caseCount = br.readLine()!!.toInt()
    repeat(caseCount) {
        val (contriesCount, flyCount) = br.readLine()!!.split(" ").map { it.toInt() }
        repeat(flyCount) {
            br.readLine()!!
        }
        bw.write("${contriesCount - 1}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
