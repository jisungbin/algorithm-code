import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val cardCount = br.readLine()!!.toInt()
    val underCards = Stack<Int>().apply {
        addAll(cardCount downTo 1)
    }
    val originalCards = ArrayDeque<Int>()
    val haveCards = br.readLine()!!.split(" ")

    var frontCard: Int

    for (i in haveCards.size - 1 downTo 0) {
        when (haveCards[i]) {
            "1" -> {
                originalCards.addFirst(underCards.pop())
            }
            "2" -> {
                frontCard = originalCards.removeFirstOrNull() ?: -1
                originalCards.addFirst(underCards.pop())
                if (frontCard != -1) originalCards.addFirst(frontCard)
            }
            "3" -> {
                originalCards.addLast(underCards.pop())
            }
        }
    }

    bw.write(originalCards.joinToString(" "))
    br.close()
    bw.flush()
    bw.close()
}
