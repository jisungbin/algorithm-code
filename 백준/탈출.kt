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

    val (columnSize, rowSize) = br.readLine()!!.split(" ").map { it.toInt() }
    val map = MutableList(columnSize) { listOf<String>() }

    val waterQueue: Queue<List<Int>> = LinkedList()
    val watersReceiveTime = MutableList(columnSize) { MutableList(rowSize) { 0 } }

    val runQueue: Queue<List<Int>> = LinkedList()
    val runsReceiveTime = MutableList(columnSize) { MutableList(rowSize) { 0 } }

    var startColumnIndex = 0
    var startRowIndex = 0
    var targetColumnIndex = 0
    var targetRowIndex = 0

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    repeat(columnSize) { columnIndex ->
        val line = br.readLine()!!.split("").filterNot { it.isBlank() }
        map[columnIndex] = line
        repeat(rowSize) { rowIndex ->
            when (line[rowIndex]) {
                "S" -> {
                    startColumnIndex = columnIndex
                    startRowIndex = rowIndex
                }
                "D" -> {
                    targetColumnIndex = columnIndex
                    targetRowIndex = rowIndex
                }
                "*" -> {
                    waterQueue.offer(listOf(columnIndex, rowIndex))  // 물 위치 넣어주기
                }
            }
        }
    }

    // 물이 도달하는 시간 구하기
    fun waterBfs() {
        while (waterQueue.isNotEmpty()) {
            val (column, row) = waterQueue.poll()

            repeat(4) { upperIndex ->
                val upperedColumn = column + columnUpper[upperIndex]
                val upperedRow = row + rowUpper[upperIndex]

                // waterReceiveTime가 vis 배열 역할도 같이 해줌
                if (
                    upperedColumn in 0 until columnSize &&
                    upperedRow in 0 until rowSize &&
                    watersReceiveTime[upperedColumn][upperedRow] == 0 && // 방문한 적 없는 곳일 때
                    map[upperedColumn][upperedRow] == "." // 비어있을 때 (그래야 물이 퍼질 수 있음)
                ) {
                    watersReceiveTime[upperedColumn][upperedRow] = watersReceiveTime[column][row] + 1
                    waterQueue.offer(listOf(upperedColumn, upperedRow))
                }
            }
        }
    }

    // 고슴도치가 비버의 굴로 가는 시간 구하기
    fun runBfs() {
        runQueue.offer(listOf(startColumnIndex, startRowIndex))

        while (runQueue.isNotEmpty()) {
            val (column, row) = runQueue.poll()

            repeat(4) { upperIndex ->
                val upperedColumn = column + columnUpper[upperIndex]
                val upperedRow = row + rowUpper[upperIndex]

                // runReceiveTime가 vis 배열 역할도 같이 해줌
                if (
                    upperedColumn in 0 until columnSize &&
                    upperedRow in 0 until rowSize &&
                    runsReceiveTime[upperedColumn][upperedRow] == 0 && // 방문한 적 없는 곳일 때
                    listOf(".", "D").contains(map[upperedColumn][upperedRow]) // 비어있는 곳이거나 비버의 집 일때
                ) {
                    val waterReceiveTime = watersReceiveTime[upperedColumn][upperedRow]
                    val newRunReceiveTime = runsReceiveTime[column][row] + 1

                    // 물이 올 수 없는 곳 이거나, 나보다 물이 더 늦게 찰 때
                    if (waterReceiveTime == 0 || waterReceiveTime > newRunReceiveTime) {
                        runsReceiveTime[upperedColumn][upperedRow] = newRunReceiveTime
                        runQueue.offer(listOf(upperedColumn, upperedRow))
                    }
                }
            }
        }
    }

    waterBfs()
    runBfs()

    val runToTargetReceiveTime = runsReceiveTime[targetColumnIndex][targetRowIndex]
    if (runToTargetReceiveTime != 0) {
        bw.write("$runToTargetReceiveTime")
    } else {
        bw.write("KAKTUS")
    }

    br.close()
    bw.flush()
    bw.close()
}
