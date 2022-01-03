import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import kotlin.math.max

@Suppress("BlockingMethodInNonBlockingContext")
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine()!!.toInt()
    val map = MutableList(size) { listOf<Int>() }
    var vis = List(size) { MutableList(size) { false } }
    var maxHeight = 0

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    repeat(size) { columnIndex ->
        val line = br.readLine()!!.split(" ").map { it.toInt() }
        map[columnIndex] = line
        maxHeight = max(maxHeight, line.maxOrNull()!!) // 다 덮이면 안전구역이 0개임
    }

    // 무조건 height 보다 더 높은 위치만 들어옴
    fun bfs(column: Int, row: Int, height: Int) {
        val bfsQueue: Queue<List<Int>> = LinkedList()
        bfsQueue.offer(listOf(column, row))
        vis[column][row] = true

        while (bfsQueue.isNotEmpty()) {
            val (_column, _row) = bfsQueue.poll()

            repeat(4) { upperIndex ->
                val upperedColumn = _column + columnUpper[upperIndex]
                val upperedRow = _row + rowUpper[upperIndex]

                if (
                    upperedColumn in 0 until size &&
                    upperedRow in 0 until size &&
                    !vis[upperedColumn][upperedRow] &&
                    map[upperedColumn][upperedRow] > height
                ) {
                    vis[upperedColumn][upperedRow] = true
                    bfsQueue.offer(listOf(upperedColumn, upperedRow))
                }
            }
        }
    }

    val maxSafeAreaCount = PriorityQueue<Int>(Collections.reverseOrder())
    repeat(maxHeight) { height ->
        var safeAreaCount = 0
        repeat(size) { columnIndex ->
            repeat(size) { rowIndex ->
                if (map[columnIndex][rowIndex] > height && !vis[columnIndex][rowIndex]) {
                    bfs(column = columnIndex, row = rowIndex, height = height)
                    safeAreaCount++
                }
            }
        }
        maxSafeAreaCount.offer(safeAreaCount)
        vis = List(size) { MutableList(size) { false } }
    }

    bw.write("${maxSafeAreaCount.poll()}")

    br.close()
    bw.flush()
    bw.close()
}
