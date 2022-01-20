import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine()!!.toInt()
    val files = List(size) { br.readLine()!! }.groupBy { it.substringAfterLast(".") }.toSortedMap { o1, o2 ->
        o1.compareTo(o2)
    }
    files.keys.forEach { key ->
        bw.write("$key ")
        bw.write("${files[key]!!.size}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
