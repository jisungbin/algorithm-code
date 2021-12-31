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

    val (columnSize, rowSize, trashCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val map = MutableList(columnSize + 1) { MutableList(rowSize + 1) { "." } } // column, row

    val vis = List(columnSize + 1) { MutableList(rowSize + 1) { false } }
    val bfsQueue: Queue<List<Int>> = LinkedList() // column, row

    val columnUpper = listOf(-1, 1, 0, 0)
    val rowUpper = listOf(0, 0, -1, 1)

    repeat(trashCount) {
        val (column, row) = br.readLine()!!.split(" ").map { it.toInt() }
        map[column][row] = "#"
    }

    fun bfs(column: Int, row: Int): Int {
        var trashSize = 1
        bfsQueue.offer(listOf(column, row))
        vis[column][row] = true
        while (bfsQueue.isNotEmpty()) {
            val (_column, _row) = bfsQueue.poll()
            repeat(4) { upperIndex ->
                val upperedColumn = _column + columnUpper[upperIndex]
                val upperedRow = _row + rowUpper[upperIndex]
                if (
                    upperedColumn in 1..columnSize &&
                    upperedRow in 1..rowSize &&
                    map[upperedColumn][upperedRow] == "#" &&
                    !vis[upperedColumn][upperedRow]
                ) {
                    bfsQueue.offer(listOf(upperedColumn, upperedRow))
                    vis[upperedColumn][upperedRow] = true
                    trashSize++
                }
            }
        }

        return trashSize
    }

    var maxTrashSize = 1
    for (columnIndex in 1 .. columnSize) {
        for (rowIndex in 1 .. rowSize) {
            if (map[columnIndex][rowIndex] == "#" && !vis[columnIndex][rowIndex]) {
                val trashSize = bfs(column = columnIndex, row = rowIndex)
                maxTrashSize = max(maxTrashSize, trashSize)
            }
        }
    }

    bw.write("$maxTrashSize")

    br.close()
    bw.flush()
    bw.close()
}
