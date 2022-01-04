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
    val map = MutableList(columnSize) { listOf<Int>() }
    val vis = List(columnSize) { List(rowSize) { MutableList(5) { false } } }

    // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4
    // 0 번째 index 는 편의를 위해 빈 값으로 두기 (1-index 시작)
    val columnUpper = listOf(0, 0, 0, 1, -1)
    val rowUpper = listOf(0, 1, -1, 0, 0)

    repeat(columnSize) { columnIndex ->
        val line = br.readLine()!!.split(" ").map { it.toInt() }
        map[columnIndex] = line
    }

    var (startColumnIndex, startRowIndex, startAngle) = br.readLine()!!.split(" ").map { it.toInt() }
    var (endColumnIndex, endRowIndex, endAngle) = br.readLine()!!.split(" ").map { it.toInt() }

    // 좌표는 0부터 시작하므로, 입력값에서 1 빼서 0-index 로 만들기
    startColumnIndex--
    startRowIndex--
    endColumnIndex--
    endRowIndex--

    fun bfs(): Int {
        val bfsQueue: Queue<List<Int>> = LinkedList()
        bfsQueue.offer(listOf(startColumnIndex, startRowIndex, startAngle, 0))

        while (bfsQueue.isNotEmpty()) {
            var (column, row, angle, count) = bfsQueue.poll()

            if (endColumnIndex == column && endRowIndex == row && endAngle == angle) return count

            count++

            // 내 앞으로 3칸 까지 나아가기
            for (times in 1..3) {
                val upedColumn = column + columnUpper[angle] * times
                val upedRow = row + rowUpper[angle] * times

                if (
                    upedColumn in 0 until columnSize &&
                    upedRow in 0 until rowSize &&
                    !vis[upedColumn][upedRow][angle]
                ) {
                    if (map[upedColumn][upedRow] == 0) {
                        vis[upedColumn][upedRow][angle] = true
                        bfsQueue.offer(listOf(upedColumn, upedRow, angle, count))
                    } else {
                        break // 벽을 만났을 땐 더 이상 갈  수 없음
                    }
                }
            }

            // 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4
            // 90도 회전만 카운트 + 1 (기본으로 +1 했음)
            // 180도 회전은 카운트 + 2 (이미 +1 했으므로, 여기선 +1만 또 해주면 됨)
            for (newAngle in 1..4) {
                if (angle != newAngle && !vis[column][row][newAngle]) {
                    vis[column][row][newAngle] = true
                    val condition = listOf(
                        angle == 1 && newAngle == 2,
                        angle == 2 && newAngle == 1,
                        angle == 3 && newAngle == 4,
                        angle == 4 && newAngle == 3
                    )
                    if (condition.contains(true)) { // 180도 회전
                        bfsQueue.offer(listOf(column, row, newAngle, count + 1))
                    } else {
                        bfsQueue.offer(listOf(column, row, newAngle, count))
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
