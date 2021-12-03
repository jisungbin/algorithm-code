import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val line = br.readLine().toInt()
    val input = br.readLine()
    val numbers = ArrayDeque<Double>()
    val operators = listOf('/', '*', '+', '-')
    val alphabets = ('A'..'Z').map { it.toString() }
    val alphabetNumbers = MutableList(line) { br.readLine().toDouble() }

    fun Char.alphabetToNumber() = alphabetNumbers[alphabets.indexOf(this.toString())]

    fun calc(operator: Char, num: Double, num2: Double) = when (operator) {
        '/' -> num / num2
        '*' -> num * num2
        '+' -> num + num2
        '-' -> num - num2
        else -> throw UnsupportedOperationException()
    }

    fun Char.isOperator() = operators.contains(this)

    input.forEach { char ->
        if (char.isOperator()) {
            val num2 = numbers.removeFirst()
            val num = numbers.removeFirst()
            numbers.addFirst(calc(char, num, num2))
        } else {
            numbers.addFirst(char.alphabetToNumber())
        }
    }
    bw.write("${String.format("%.2f", numbers.first())}\n")
    br.close()
    bw.flush()
    bw.close()
}
