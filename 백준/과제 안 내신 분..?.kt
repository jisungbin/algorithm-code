import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val numbers = mutableListOf<Int>()
    repeat(28) {
        numbers.add(br.readLine()!!.toInt())
    }
    val result = PriorityQueue((1..30) - numbers.toSet())
    bw.write("${result.poll()}\n")
    bw.write("${result.poll()}")

    br.close()
    bw.flush()
    bw.close()
}
