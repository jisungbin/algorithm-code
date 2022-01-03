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

    val (columnSize, rowSize, boxCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val map = List(columnSize) { MutableList(rowSize) { 0 } }

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    repeat(boxCount) {
        val (row, column, row2, column2) = br.readLine()!!.split(" ").map { it.toInt() }
        for (columnIndex in column until column2) {
            for (rowIndex in row until row2) {
                map[columnIndex][rowIndex] = 1
            }
        }
    }

    // map[_column][_row]가 0인 것만 들어옴
    fun bfs(_column: Int, _row: Int): Int {
        var count = 1
        val bfsQueue: Queue<List<Int>> = LinkedList()
        bfsQueue.offer(listOf(_column, _row))
        map[_column][_row] = 1

        while (bfsQueue.isNotEmpty()) {
            val (column, row) = bfsQueue.poll()

            repeat(4) { upperIndex ->
                val upperedColumn = column + columnUpper[upperIndex]
                val upperedRow = row + rowUpper[upperIndex]

                // map과 동시에 vis 배열 역할을 해줌
                if (
                    upperedColumn in 0 until columnSize &&
                    upperedRow in 0 until rowSize &&
                    map[upperedColumn][upperedRow] == 0 // 빈 공간일 때
                ) {
                    map[upperedColumn][upperedRow] = 1
                    bfsQueue.offer(listOf(upperedColumn, upperedRow))
                    count++
                }
            }
        }

        return count
    }

    val boxes = mutableListOf<Int>()
    repeat(columnSize) { columnIndex ->
        repeat(rowSize) { rowIndex ->
            if (map[columnIndex][rowIndex] == 0) {
                boxes.add(bfs(columnIndex, rowIndex))
            }
        }
    }
    bw.write("${boxes.size}\n")
    bw.write(boxes.sorted().joinToString(" "))

    br.close()
    bw.flush()
    bw.close()
}
