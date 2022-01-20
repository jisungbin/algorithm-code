import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

private val keyboards = listOf("qwertyuiop", "asdfghjkl", "zxcvbnm").map { it.toList().map(Char::toString) }

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    fun String.indexOfKeyboard(): List<Int> {
        keyboards.forEachIndexed { columnIndex, keyboard ->
            val rowIndex = keyboard.indexOf(this)
            if (rowIndex != -1) {
                return listOf(columnIndex, rowIndex)
            }
        }
        throw Exception("Unknown keyboard char.")
    }

    val (startLeft, startRight) = br.readLine()!!.split(" ")
    val word = br.readLine()!!.toList().map(Char::toString)

    var (startLeftColumn, startLeftRow) = startLeft.indexOfKeyboard()
    var (startRightColumn, startRightRow) = startRight.indexOfKeyboard()
    var (time, distance) = listOf(word.size, 0)

    word.forEach { char ->
        val (column, row) = char.indexOfKeyboard()
        if ((column <= 1 && row <= 4) || (column == 2 && row <= 3)) { // 자음
            distance += abs(startLeftColumn - column) + abs(startLeftRow - row)
            startLeftColumn = column
            startLeftRow = row
        } else { // 모음
            distance += abs(startRightColumn - column) + abs(startRightRow - row)
            startRightColumn = column
            startRightRow = row
        }
    }

    time += distance
    bw.write("$time")

    br.close()
    bw.flush()
    bw.close()
}
