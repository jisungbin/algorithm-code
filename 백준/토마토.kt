import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (rowSize, columnSize) = br.readLine()!!.split(" ").map { it.toInt() }
    val mapOrDayCount = MutableList(columnSize) { mutableListOf<Int>() }
    val bfsQueue: Queue<List<Int>> = LinkedList()

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    repeat(columnSize) { columnIndex ->
        val line = br.readLine()!!.split(" ").map { it.toInt() }
        repeat(rowSize) { rowIndex ->
            if (line[rowIndex] == 1) {
                bfsQueue.offer(listOf(columnIndex, rowIndex))
            }
        }
        mapOrDayCount[columnIndex] = line.toMutableList()
    }

    while (bfsQueue.isNotEmpty()) {
        val (column, row) = bfsQueue.poll()
        repeat(4) { upperIndex ->
            val upperedColumn = column + columnUpper[upperIndex]
            val upperedRow = row + rowUpper[upperIndex]

            // map과 동시에 vis 배열 역할을 해줌
            // map[column][row]가 1인 것들만 들어옴
            if (
                upperedColumn in 0 until columnSize &&
                upperedRow in 0 until rowSize &&
                mapOrDayCount[upperedColumn][upperedRow] == 0
            ) {
                mapOrDayCount[upperedColumn][upperedRow] = mapOrDayCount[column][row] + 1
                bfsQueue.offer(listOf(upperedColumn, upperedRow))
            }
        }
    }

    var day = 0
    column@for (columnIndex in 0 until columnSize) {
        for (rowIndex in 0 until rowSize) {
            val tomato = mapOrDayCount[columnIndex][rowIndex]
            if (tomato == 0) {
                day = -1
                break@column
            }
            day = max(day, tomato)
        }
    }

    if (day != -1) {
        day--
    }

    bw.write("$day")

    br.close()
    bw.flush()
    bw.close()
}
