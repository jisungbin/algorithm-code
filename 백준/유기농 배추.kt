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

    val caseCount = br.readLine()!!.toInt()
    repeat(caseCount) {
        val (rowSize, columnSize, cabbageSize) = br.readLine()!!.split(" ").map { it.toInt() }
        val map = List(columnSize) { MutableList(rowSize) { 0 } }

        val columnUpper = listOf(1, -1, 0, 0)
        val rowUpper = listOf(0, 0, 1, -1)

        repeat(cabbageSize) {
            val (row, column) = br.readLine()!!.split(" ").map { it.toInt() }
            map[column][row] = 1
        }

        // map[_column][_row]가 1인 것만 들어옴
        fun bfs(_column: Int, _row: Int) {
            val bfsQueue: Queue<List<Int>> = LinkedList()
            bfsQueue.offer(listOf(_column, _row))
            map[_column][_row] = 0

            while (bfsQueue.isNotEmpty()) {
                val (column, row) = bfsQueue.poll()

                repeat(4) { upperIndex ->
                    val upperedColumn = column + columnUpper[upperIndex]
                    val upperedRow = row + rowUpper[upperIndex]

                    // map과 동시에 vis 배열 역할을 해줌
                    if (
                        upperedColumn in 0 until columnSize &&
                        upperedRow in 0 until rowSize &&
                        map[upperedColumn][upperedRow] == 1 //  배추가 있을 때
                    ) {
                        map[upperedColumn][upperedRow] = 0
                        bfsQueue.offer(listOf(upperedColumn, upperedRow))
                    }
                }
            }
        }

        var cabbageCount = 0
        repeat(columnSize) { columnIndex ->
            repeat(rowSize) { rowIndex ->
                if (map[columnIndex][rowIndex] == 1) { // 배추가 있을 때
                    bfs(columnIndex, rowIndex)
                    cabbageCount++
                }
            }
        }

        bw.write("$cabbageCount\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
