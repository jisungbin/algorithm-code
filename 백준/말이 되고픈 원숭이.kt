import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

private data class Node(
    val columnIndex: Int = 0,
    val rowIndex: Int = 0,
    val moveCoint: Int = 0,
    val useAbilityCount: Int = 0
)

@Suppress("BlockingMethodInNonBlockingContext")
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val usableAbilityCount = br.readLine()!!.toInt()
    val (rowSize, columnSize) = br.readLine()!!.split(" ").map { it.toInt() }
    val map = MutableList(columnSize) { br.readLine()!!.split(" ").map { it.toInt() } }

    val vis = List(columnSize) { MutableList(rowSize) { MutableList(usableAbilityCount + 1) { false } } }

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)
    val abilityColumnUpper = listOf(-2, -1, 1, 2, 2, 1, -1, -2)
    val abilityRowUpper = listOf(-1, -2, -2, -1, 1, 2, 2, 1)

    fun bfs(): Int {
        val bfsQueue: Queue<Node> = LinkedList()
        vis[0][0][0] = true
        bfsQueue.offer(Node())

        while (bfsQueue.isNotEmpty()) {
            val (column, row, moveCount, useAbilityCount) = bfsQueue.poll()

            if (column == columnSize - 1 && row == rowSize - 1) {
                return moveCount
            }

            // bfs 특정상 제일 먼저 도착한 곳이 최단 거리임!!
            // 모든 케이스를 다 넣음

            // 능력을 사용할 수 있을 때
            if (useAbilityCount < usableAbilityCount) {
                repeat(8) { upperIndex ->
                    val upedColumn = column + abilityColumnUpper[upperIndex]
                    val upedRow = row + abilityRowUpper[upperIndex]

                    // 만약 이동할 곳이 평지이고,
                    // 이동할 곳을 방문하지 않았을 때
                    if (
                        upedColumn in 0 until columnSize &&
                        upedRow in 0 until rowSize &&
                        map[upedColumn][upedRow] == 0 &&
                        !vis[upedColumn][upedRow][useAbilityCount + 1]
                    ) {
                        vis[upedColumn][upedRow][useAbilityCount + 1] = true
                        bfsQueue.offer(
                            Node(
                                columnIndex = upedColumn,
                                rowIndex = upedRow,
                                moveCoint = moveCount + 1,
                                useAbilityCount = useAbilityCount + 1
                            )
                        )
                    }
                }
            }

            // 그냥 이동했을 때
            repeat(4) { upperIndex ->
                val upedColumn = column + columnUpper[upperIndex]
                val upedRow = row + rowUpper[upperIndex]

                // 만약 이동할 곳이 평지이고,
                // 이동할 곳을 방문하지 않았을 때
                if (
                    upedColumn in 0 until columnSize &&
                    upedRow in 0 until rowSize &&
                    map[upedColumn][upedRow] == 0 &&
                    !vis[upedColumn][upedRow][useAbilityCount]
                ) {
                    vis[upedColumn][upedRow][useAbilityCount] = true
                    bfsQueue.offer(
                        Node(
                            columnIndex = upedColumn,
                            rowIndex = upedRow,
                            moveCoint = moveCount + 1,
                            useAbilityCount = useAbilityCount
                        )
                    )
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
