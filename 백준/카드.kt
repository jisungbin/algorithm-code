import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val cardsGroup = List(br.readLine()!!.toInt()) { br.readLine()!!.toLong() }.groupBy { it }
    val manyHaveCardCount = cardsGroup.maxOf { it.value.size }
    val manyHaveCards = cardsGroup.filter { it.value.size == manyHaveCardCount }
    val value = manyHaveCards.keys.minOf { it }
    bw.write("$value")
    br.close()
    bw.flush()
    bw.close()
}
