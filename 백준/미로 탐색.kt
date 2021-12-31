import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (columnSize, rowSize) = br.readLine()!!.split(" ").map { it.toInt() }
    val mapOrVisitCount = MutableList(columnSize) { MutableList(rowSize) { 0 } } // column, row

    val vis = List(columnSize) { MutableList(rowSize) { false } }
    val bfsQueue: Queue<List<Int>> = LinkedList() // column, row

    val columnUpper = listOf(-1, 1, 0, 0)
    val rowUpper = listOf(0, 0, -1, 1)

    repeat(columnSize) { columnIndex ->
        val input = br.readLine()!!.split("").filterNot { it.isBlank() }.map { it.toInt() }
        mapOrVisitCount[columnIndex] = input.toMutableList()
    }

    // 미로 시작 부분
    bfsQueue.offer(listOf(0, 0))
    vis[0][0] = true
    mapOrVisitCount[0][0] = 1

    // BFS 시작
    while (bfsQueue.isNotEmpty()) {
        val (column, row) = bfsQueue.poll()
        repeat(4) { upperIndex ->
            val upperedColumn = column + columnUpper[upperIndex]
            val upperedRow = row + rowUpper[upperIndex]
            if (
                upperedColumn in 0 until columnSize &&
                upperedRow in 0 until rowSize &&
                mapOrVisitCount[upperedColumn][upperedRow] == 1 &&
                !vis[upperedColumn][upperedRow]
            ) {
                bfsQueue.offer(listOf(upperedColumn, upperedRow))
                vis[upperedColumn][upperedRow] = true
                mapOrVisitCount[upperedColumn][upperedRow] = mapOrVisitCount[column][row] + 1
            }
        }
    }

    bw.write("${mapOrVisitCount[columnSize - 1][rowSize - 1]}")

    br.close()
    bw.flush()
    bw.close()
}
