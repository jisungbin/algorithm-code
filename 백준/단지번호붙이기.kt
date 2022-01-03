import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine()!!.toInt()
    val map = List(size) { br.readLine()!!.split("").filterNot { it.isBlank() }.map { it.toInt() }.toMutableList() }

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    // map[_column][_row]가 1인 것만 들어옴
    fun bfs(_column: Int, _row: Int): Int {
        var count = 1
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
                    upperedColumn in 0 until size &&
                    upperedRow in 0 until size &&
                    map[upperedColumn][upperedRow] == 1 // 집이 있을 때
                ) {
                    map[upperedColumn][upperedRow] = 0
                    bfsQueue.offer(listOf(upperedColumn, upperedRow))
                    count++
                }
            }
        }
        return count
    }

    val housesNumber = mutableListOf<Int>()
    repeat(size) { columnIndex ->
        repeat(size) { rowIndex ->
            if (map[columnIndex][rowIndex] == 1) { // 집일때
                housesNumber.add(bfs(columnIndex, rowIndex))
            }
        }
    }

    bw.write("${housesNumber.size}\n")
    bw.write(housesNumber.sorted().joinToString("\n"))

    br.close()
    bw.flush()
    bw.close()
}
