import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

@Suppress("BlockingMethodInNonBlockingContext")
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (_, queryCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val lights = br.readLine()!!.split(" ").map { it.toInt() }.toMutableList()
    val queries = List(queryCount) { br.readLine()!!.split(" ").map { it.toInt() } }

    queries.forEach { query ->
        val (num, a, b) = query
        when (num) {
            1 -> {
                lights[a - 1] = b
            }
            else -> {
                for (index in (a - 1) until b) {
                    when (num) {
                        2 -> lights[index] = if (lights[index] == 0) 1 else 0
                        3 -> lights[index] = 0
                        4 -> lights[index] = 1
                    }
                }
            }
        }
    }

    bw.write(lights.joinToString(" "))

    br.close()
    bw.flush()
    bw.close()
}
