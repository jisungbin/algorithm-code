import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

@Suppress("BlockingMethodInNonBlockingContext")
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (columnSize, rowSize) = br.readLine()!!.split(" ").map { it.toInt() }
    val map = MutableList(columnSize) { listOf<String>() }

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    repeat(columnSize) { columnIndex ->
        val line = br.readLine()!!.split("").filterNot { it.isBlank() }
        map[columnIndex] = line
    }

    fun bfs(column: Int, row: Int): Int {
        val bfsQueue: Queue<List<Int>> = LinkedList()
        val vis = MutableList(columnSize) { MutableList(rowSize) { 0 } }
        vis[column][row] = 1
        bfsQueue.offer(listOf(column, row))

        var largestGoldWayTime = 0

        while (bfsQueue.isNotEmpty()) {
            val (_column, _row) = bfsQueue.poll()

            repeat(4) { upperIndex ->
                val upperedColumn = _column + columnUpper[upperIndex]
                val upperedRow = _row + rowUpper[upperIndex]

                if (
                    upperedColumn in 0 until columnSize &&
                    upperedRow in 0 until rowSize &&
                    vis[upperedColumn][upperedRow] == 0 && // 아직 방문하지 않았을 때
                    map[upperedColumn][upperedRow] == "L" // 육지일 때
                ) {
                    vis[upperedColumn][upperedRow] = vis[_column][_row] + 1
                    bfsQueue.offer(listOf(upperedColumn, upperedRow))
                    largestGoldWayTime = max(largestGoldWayTime, vis[upperedColumn][upperedRow])
                }
            }
        }

        return largestGoldWayTime - 1
    }

    var largestGoldWayTime = 0
    repeat(columnSize) { columnIndex ->
        repeat(rowSize) { rowIndex ->
            if (map[columnIndex][rowIndex] == "L") {
                largestGoldWayTime = max(largestGoldWayTime, bfs(columnIndex, rowIndex))
            }
        }
    }

    bw.write("$largestGoldWayTime")

    br.close()
    bw.flush()
    bw.close()
}
