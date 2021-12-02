import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val index = br.readLine().toInt()
    val deque = ArrayDeque<Int>()
    val result = MutableList(index) { br.readLine().toInt() }
    val operators = mutableListOf<String>()

    fun push(number: Int) {
        deque.addFirst(number)
        operators.add("+")
    }

    fun checkAndPop() {
        while (result.isNotEmpty() && deque.isNotEmpty() && result.first() == deque.first()) {
            result.removeAt(0)
            deque.removeFirst()
            operators.add("-")
        }
    }

    repeat(index) { _index ->
        push(_index + 1)
        checkAndPop()
    }

    if (operators.count { it == "+" } != operators.count { it == "-" }) {
        bw.write("NO\n")
    } else {
        bw.write(operators.joinToString(separator = "\n", postfix = "\n"))
    }

    br.close()
    bw.flush()
    bw.close()
}
