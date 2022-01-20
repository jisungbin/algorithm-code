import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val columnUpper = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
private val rowUpper = listOf(-1, 0, 1, -1, 1, -1, 0, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine()!!.toInt()
    val boomMap = List(N) { MutableList(N) { "." } }
    val map = List(N) { column ->
        br.readLine()!!.toList().mapIndexed { row, char ->
            if (char == '*') {
                boomMap[column][row] = "*"
            }
            char
        }
    }
    val input = List(N) { br.readLine()!!.toList() }
    val newMap = List(N) { MutableList(N) { "." } }

    fun findNearByBoomCount(column: Int, row: Int): Int {
        var boomCount = 0
        repeat(8) { upperIndex ->
            val uppedColumn = column + columnUpper[upperIndex]
            val uppedRow = row + rowUpper[upperIndex]
            if (uppedColumn in 0 until N && uppedRow in 0 until N) {
                if (map[uppedColumn][uppedRow] == '*') boomCount++
            }
        }
        return boomCount
    }

    var printBoomMap = false

    repeat(N) { column ->
        repeat(N) { row ->
            if (input[column][row] == 'x') {
                if (map[column][row] == '.') {
                    val boomCount = findNearByBoomCount(column, row).toString()
                    boomMap[column][row] = boomCount
                    newMap[column][row] = boomCount
                } else { // 폭탄 클릭
                    printBoomMap = true
                }
            }
        }
    }

    val printMap = when (printBoomMap) {
        true -> boomMap
        else -> newMap
    }
    bw.write(printMap.joinToString("\n") { it.joinToString("") })

    br.close()
    bw.flush()
    bw.close()
}
