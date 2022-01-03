import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue
import kotlin.math.min

@Suppress("BlockingMethodInNonBlockingContext")
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine()!!.toInt()
    val map = MutableList(size) { br.readLine()!!.split(" ").map { it.toInt() }.toMutableList() }

    var vis = List(size) { MutableList(size) { false } }

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    fun setMapForEachAreaNumber(column: Int, row: Int, number: Int) {
        val bfsQueue: Queue<List<Int>> = LinkedList()
        vis[column][row] = true
        map[column][row] = number
        bfsQueue.offer(listOf(column, row))

        while (bfsQueue.isNotEmpty()) {
            val (_column, _row) = bfsQueue.poll()

            repeat(4) { upperIndex ->
                val upedColumn = _column + columnUpper[upperIndex]
                val upedRow = _row + rowUpper[upperIndex]

                if (
                    upedColumn in 0 until size &&
                    upedRow in 0 until size &&
                    !vis[upedColumn][upedRow] && // 아직 방문한 적이 없을 때
                    map[upedColumn][upedRow] == 1 // 육지일 때
                ) {
                    vis[upedColumn][upedRow] = true
                    map[upedColumn][upedRow] = number // 육지 번호 업데이트
                    bfsQueue.offer(listOf(upedColumn, upedRow))
                }
            }
        }
    }

    fun findShortLenghtToConnectOtherAreaFromStartAreaNumber(number: Int): Int {
        val bfsQueue: Queue<List<Int>> = LinkedList()

        // number인 육지 좌표 모두 찾아서 넣기
        repeat(size) { columnIndex ->
            repeat(size) { rowIndex ->
                if (map[columnIndex][rowIndex] == number) {
                    vis[columnIndex][rowIndex] = true
                    bfsQueue.offer(listOf(columnIndex, rowIndex))
                }
            }
        }

        var shortLenght = 0
        while (bfsQueue.isNotEmpty()) {
            // 현재 bfsQueue에 있는게 내 섬에서 한 번에 갈 수 있는 곳들임
            // -> 현재 bfsQueue에 있는 만큼 돌리는걸 한 턴으로 해야 함
            repeat(bfsQueue.size) {
                val (column, row) = bfsQueue.poll()

                repeat(4) { upperIndex ->
                    val upedColumn = column + columnUpper[upperIndex]
                    val upedRow = row + rowUpper[upperIndex]

                    if (upedColumn in 0 until size && upedRow in 0 until size && !vis[upedColumn][upedRow]) {
                        val land = map[upedColumn][upedRow]
                        if (land == 0) { // 바다 일 때
                            vis[upedColumn][upedRow] = true
                            bfsQueue.offer(listOf(upedColumn, upedRow))
                        } else if (land != number) { // 다른 섬에 도착 했을 때
                            return shortLenght
                        }
                    }
                }
            }

            shortLenght++
        }

        throw Error("no way.")
    }

    var number = 0
    repeat(size) { columnIndex ->
        repeat(size) { rowIndex ->
            // 아직 방문한 적 없는 육지라면
            if (!vis[columnIndex][rowIndex] && map[columnIndex][rowIndex] == 1) {
                setMapForEachAreaNumber(column = columnIndex, row = rowIndex, number = ++number)
            }
        }
    }

    var shortLenght = Int.MAX_VALUE
    for (areaNumber in 1..number) {
        vis = List(size) { MutableList(size) { false } }
        shortLenght = min(shortLenght, findShortLenghtToConnectOtherAreaFromStartAreaNumber(areaNumber))
    }

    bw.write("$shortLenght")
    // bw.write(map.joinToString("\n") { it.joinToString(" ") })

    br.close()
    bw.flush()
    bw.close()
}
