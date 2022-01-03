import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

@Suppress("BlockingMethodInNonBlockingContext")
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine()!!.toInt()
    val map = MutableList(size) { listOf<String>() }
    var vis = List(size) { MutableList(size) { false } }

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    repeat(size) { columnIndex ->
        val line = br.readLine()!!.split("").filterNot { it.isBlank() }
        map[columnIndex] = line
    }

    fun bfs(column: Int, row: Int, targets: List<String>) {
        val bfsQueue: Queue<List<Int>> = LinkedList()
        vis[column][row] = true
        bfsQueue.offer(listOf(column, row))

        while (bfsQueue.isNotEmpty()) {
            val (_column, _row) = bfsQueue.poll()

            repeat(4) { upperIndex ->
                val upperedColumn = _column + columnUpper[upperIndex]
                val upperedRow = _row + rowUpper[upperIndex]

                if (
                    upperedColumn in 0 until size &&
                    upperedRow in 0 until size &&
                    !vis[upperedColumn][upperedRow] &&
                    targets.contains(map[upperedColumn][upperedRow])
                ) {
                    vis[upperedColumn][upperedRow] = true
                    bfsQueue.offer(listOf(upperedColumn, upperedRow))
                }
            }
        }
    }

    // 일반인 기준
    var normalPeopleCanSeePartCount = 0
    repeat(size) { columnIndex ->
        repeat(size) { rowIndex ->
            if (!vis[columnIndex][rowIndex]) {
                normalPeopleCanSeePartCount++
                bfs(
                    column = columnIndex,
                    row = rowIndex,
                    targets = listOf(map[columnIndex][rowIndex])
                )
            }
        }
    }

    // 특별한 분들 기준
    vis = List(size) { MutableList(size) { false } }
    var specialPeopleCanSeePartCount = 0
    repeat(size) { columnIndex ->
        repeat(size) { rowIndex ->
            if (!vis[columnIndex][rowIndex]) {
                val tile = map[columnIndex][rowIndex]
                specialPeopleCanSeePartCount++
                if (tile == "B") {
                    bfs(
                        column = columnIndex,
                        row = rowIndex,
                        targets = listOf("B")
                    )
                } else {
                    bfs(
                        column = columnIndex,
                        row = rowIndex,
                        targets = listOf("G", "R")
                    )
                }
            }
        }
    }

    bw.write("$normalPeopleCanSeePartCount $specialPeopleCanSeePartCount")

    br.close()
    bw.flush()
    bw.close()
}
