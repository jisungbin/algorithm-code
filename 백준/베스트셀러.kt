import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val soldedBooks = List(br.readLine()!!.toInt()) { br.readLine()!! }.groupBy { it }
    val mostSoldedBookCount = soldedBooks.maxOf { it.value.size }
    val mostSoldedBooksName = soldedBooks.filter { it.value.size == mostSoldedBookCount }.keys.minOf { it }

    bw.write(mostSoldedBooksName)
    br.close()
    bw.flush()
    bw.close()
}
