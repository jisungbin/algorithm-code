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
    val map = MutableList(columnSize) { mutableListOf<Int>() }

    var virusCount = 0
    var wallCount = 0
    val canBuildWallMap = mutableListOf<List<Int>>()

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    repeat(columnSize) { columnIndex ->
        val line = br.readLine()!!.split(" ").map { it.toInt() }
        map[columnIndex] = line.toMutableList()
        repeat(rowSize) { rowIndex ->
            when (line[rowIndex]) {
                0 -> canBuildWallMap.add(listOf(columnIndex, rowIndex)) // 현재 빈 곳이니, 벽 세울 수 있는 위치에 저장
                1 -> wallCount++
                2 -> virusCount++
            }
        }
    }

    fun bfs(): Int {
        val bfsQueue: Queue<List<Int>> = LinkedList()
        val vis = List(columnSize) { MutableList(rowSize) { false } }
        var _virusCount = virusCount

        repeat(columnSize) { columnIndex ->
            repeat(rowSize) { rowIndex ->
                if (map[columnIndex][rowIndex] == 2) {
                    bfsQueue.offer(listOf(columnIndex, rowIndex))
                }
            }
        }

        while (bfsQueue.isNotEmpty()) {
            val (column, row) = bfsQueue.poll()

            repeat(4) { upperIndex ->
                val upperedColumn = column + columnUpper[upperIndex]
                val upperedRow = row + rowUpper[upperIndex]

                if (
                    upperedColumn in 0 until columnSize &&
                    upperedRow in 0 until rowSize &&
                    !vis[upperedColumn][upperedRow] &&
                    map[upperedColumn][upperedRow] == 0 // 빈 칸이면 오염됨
                ) {
                    vis[upperedColumn][upperedRow] = true
                    bfsQueue.offer(listOf(upperedColumn, upperedRow))
                    _virusCount++
                }
            }
        }

        // 전체 크기 - (오염된 곳 개수 + 원래 있던 벽 개수 + 새로 추가한 벽 3개) = 남은 안전 구역 개수
        return columnSize * rowSize - (_virusCount + wallCount + 3)
    }

    var maxSafeAreaCount = 0

    // 벽 3개 모든 위치에 세우기 시뮬레이션
    for (index in 0 until canBuildWallMap.size) {
        for (index2 in index + 1 until canBuildWallMap.size) {
            for (index3 in index2 + 1 until canBuildWallMap.size) {
                // 세울 수 있는 모든 벽의 위치들
                val (column, row) = canBuildWallMap[index]
                val (column2, row2) = canBuildWallMap[index2]
                val (column3, row3) = canBuildWallMap[index3]

                // 벽 세우기
                map[column][row] = 1
                map[column2][row2] = 1
                map[column3][row3] = 1

                maxSafeAreaCount = max(maxSafeAreaCount, bfs())

                // 세운 벽 다시 부시기
                map[column][row] = 0
                map[column2][row2] = 0
                map[column3][row3] = 0
            }
        }
    }

    bw.write("$maxSafeAreaCount")

    br.close()
    bw.flush()
    bw.close()
}
