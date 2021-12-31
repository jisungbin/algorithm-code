import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

private const val MAX = 50 + 1

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (columnSize, rowSize) = br.readLine()!!.split(" ").map { it.toInt() }
    val map = MutableList(columnSize) { mutableListOf<Int>() }
    val bfsQueue: Queue<List<Int>> = LinkedList()

    val columnUpper = listOf(1, -1, 0, 0, 1, -1, 1, -1)
    val rowUpper = listOf(0, 0, 1, -1, 1, -1, -1, 1)

    repeat(columnSize) { columnIndex ->
        val _map = br.readLine()!!.split(" ").map { it.toInt() }
        repeat(rowSize) { rowIndex ->
            if (_map[rowIndex] == 1) {
                bfsQueue.offer(listOf(columnIndex, rowIndex))
            }
        }
        map[columnIndex] = _map.toMutableList()
    }

    while (bfsQueue.isNotEmpty()) {
        val (column, row) = bfsQueue.poll()
        repeat(8) { upperIndex ->
            val upperedColumn = column + columnUpper[upperIndex]
            val upperedRow = row + rowUpper[upperIndex]

            // map과 동시에 vis 배열 역할을 해줌
            if (
                upperedColumn in 0 until columnSize &&
                upperedRow in 0 until rowSize &&
                map[upperedColumn][upperedRow] == 0
            ) {
                map[upperedColumn][upperedRow] = map[column][row] + 1
                bfsQueue.offer(listOf(upperedColumn, upperedRow))
            }
        }
    }

    var distance = 0
    repeat(columnSize) { columnIndex ->
        repeat(rowSize) { rowIndex ->
            distance = max(distance, map[columnIndex][rowIndex])
        }
    }
    distance--

    bw.write("$distance")

    br.close()
    bw.flush()
    bw.close()
}
