import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    br.readLine()
    val ownCards = br.readLine()!!.split(" ").map { it.toInt() }.toMutableList()
    br.readLine()
    val cardNumbers = br.readLine()!!.split(" ").map { it.toInt() }
    val groupedCards = ownCards.groupBy { it }
    val haveCardCounts = mutableListOf<Int>()

    cardNumbers.forEach { number ->
        if (groupedCards.containsKey(number)) {
            haveCardCounts.add(groupedCards.getValue(number).size)
        } else {
            haveCardCounts.add(0)
        }
    }

    bw.write("${haveCardCounts.joinToString(" ")}\n")
    br.close()
    bw.flush()
    bw.close()
}
