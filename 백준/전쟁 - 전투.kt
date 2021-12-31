import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import kotlin.math.pow

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    // 상하좌우 확인하기 위한 배열
    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    val (rowSize, columnSize) = br.readLine()!!.split(" ").map { it.toInt() }
    val vis = List(columnSize) { MutableList(rowSize) { false } }
    val map = MutableList(columnSize) { emptyList<String>() }

    var (blue, white) = listOf(0, 0)
    val bfsQueue: Queue<List<Int>> = LinkedList() // y, x 좌표

    repeat(columnSize) { columnIndex ->
        val input = br.readLine()!!.split("").filterNot { it.isBlank() }
        map[columnIndex] = input
    }

    fun bfs(column: Int, row: Int): Int {
        var power = 1
        bfsQueue.add(listOf(column, row))
        vis[column][row] = true

        while (bfsQueue.isNotEmpty()) {
            val (_column, _row) = bfsQueue.poll()
            repeat(4) { upperIndex ->
                val _columnUpper = _column + columnUpper[upperIndex]
                val _rowUpper = _row + rowUpper[upperIndex]
                if (
                    _columnUpper in 0 until columnSize &&
                    _rowUpper in 0 until rowSize &&
                    map[_columnUpper][_rowUpper] == map[_column][_row] &&
                    !vis[_columnUpper][_rowUpper]
                ) { // map[_columnUpper][_rowUpper] == map[_column][_row] 맞으면 뭉쳐있는거
                    vis[_columnUpper][_rowUpper] = true
                    bfsQueue.add(listOf(_columnUpper, _rowUpper))
                    power++
                }
            }
        }
        return power
    }

    repeat(columnSize) { columnIndex ->
        repeat(rowSize) { rowIndex ->
            if (!vis[columnIndex][rowIndex]) {
                val power = bfs(column = columnIndex, row = rowIndex).toDouble().pow(2).toInt()
                if (map[columnIndex][rowIndex] == "W") {
                    white += power
                } else {
                    blue += power
                }
            }
        }
    }

    bw.write("$white $blue")

    br.close()
    bw.flush()
    bw.close()
}
