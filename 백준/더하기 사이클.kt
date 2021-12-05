import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val originalNumber = br.readLine()!!
    var calcNumber = originalNumber
    var turn = 0

    while (true) {
        if (calcNumber.toInt() < 10) calcNumber = "0$calcNumber"
        val splitedCalcNumber = calcNumber.split("").filterNot { it.isBlank() }.map { it.toInt() }
        val a = splitedCalcNumber.last()
        val b = splitedCalcNumber.sum().toString().split("").filterNot { it.isBlank() }.last()
        calcNumber = "$a$b".toInt().toString()
        turn++
        if (calcNumber == originalNumber) {
            break
        }
    }

    bw.write("$turn\n")
    br.close()
    bw.flush()
    bw.close()
}
