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

    val (columnSize, rowSize, canPunchCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val map = MutableList(columnSize) { br.readLine()!!.split("").filterNot { it.isBlank() }.map { it.toInt() } }

    // columnIndex, rowIndex, punched (0: false, 1~: punched-count) -> visit count
    val vis = List(columnSize) { MutableList(rowSize) { MutableList(canPunchCount + 1) { 0 } } }

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    fun bfs(): Int {
        // columnIndex, rowIndex, punched (0: false, 1~: punched-count)
        val bfsQueue: Queue<List<Int>> = LinkedList()
        vis[0][0][0] = 1
        bfsQueue.offer(listOf(0, 0, 0))

        while (bfsQueue.isNotEmpty()) {
            val (column, row, punched) = bfsQueue.poll()

            if (column == columnSize - 1 && row == rowSize - 1) {
                return vis[column][row][punched]
            }

            repeat(4) { upperIndex ->
                val upedColumn = column + columnUpper[upperIndex]
                val upedRow = row + rowUpper[upperIndex]

                if (upedColumn in 0 until columnSize && upedRow in 0 until rowSize) {
                    // bfs 특정상 제일 먼저 도착한 곳이 최단 거리임!!
                    // 내가 갈 수 있는 곳에 벽이 있고, 아직 부신 벽이 없다면
                    // 해당 벽을 부신 케이스를 매번 추가하면서
                    // 제일 먼저 도착지에 도착한 케이스가 최단 경로임

                    // 만약 갈 곳이 벽이고, 아직 펀치할 수 있는 기회가 남았다면
                    // 그리고 만약 펀치해서 간 곳이 아직 방문하지 않은 곳이라면
                    if (
                        map[upedColumn][upedRow] == 1 &&
                        punched < canPunchCount &&
                        vis[upedColumn][upedRow][punched + 1] == 0
                    ) {
                        vis[upedColumn][upedRow][punched + 1] = vis[column][row][punched] + 1
                        bfsQueue.offer(listOf(upedColumn, upedRow, punched + 1))
                    }
                    // 만약 갈 수 있는 길이고, 아직 방문하지 않았다면
                    if (map[upedColumn][upedRow] == 0 && vis[upedColumn][upedRow][punched] == 0) {
                        vis[upedColumn][upedRow][punched] = vis[column][row][punched] + 1
                        bfsQueue.offer(listOf(upedColumn, upedRow, punched))
                    }
                }
            }
        }

        return -1
    }

    bw.write("${bfs()}")

    br.close()
    bw.flush()
    bw.close()
}
