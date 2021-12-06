import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val deque = ArrayDeque((1..br.readLine()!!.split(" ")[0].toInt()).toList())
    val pickWantNumbers = br.readLine()!!.split(" ").map { it.toInt() }.toMutableList()
    var count = 0

    while (pickWantNumbers.isNotEmpty()) {
        val pickWantNumber = pickWantNumbers.first()
        if (deque.first() == pickWantNumber) {
            pickWantNumbers.removeFirst()
            deque.removeFirst()
        } else {
            val centerIndex = ceil(deque.size.toDouble() / 2)
            val pickWantNumberIndex = deque.indexOf(pickWantNumber) + 1
            if (centerIndex >= pickWantNumberIndex) {
                deque.addLast(deque.removeFirst())
            } else {
                deque.addFirst(deque.removeLast())
            }
            count++
        }
    }

    bw.write("$count\n")
    br.close()
    bw.flush()
    bw.close()
}
