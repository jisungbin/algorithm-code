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

    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    repeat(caseCount) {
        val (columnSize, rowSize) = br.readLine()!!.split(" ").map { it.toInt() }
        val map = MutableList(columnSize + 2) { mutableListOf<Char>() }
        var vis = List(columnSize + 2) { MutableList(rowSize + 2) { false } }
        val keys = MutableList(26) { false } // 'a' ~ 'z'
        var documentCount = 0

        fun setDoorOpenByKey(key: Char) {
            for (columnIndex in 1..columnSize) {
                for (rowIndex in 1..rowSize) {
                    // 열쇠의 대문자가 이 열쇠로 열 수 있는 문
                    if (map[columnIndex][rowIndex].toString() == key.uppercase()) {
                        map[columnIndex][rowIndex] = '.'
                    }
                }
            }
        }

        fun bfs() {
            var bfsQueue: Queue<List<Int>> = LinkedList()
            bfsQueue.offer(listOf(0, 0))

            while (bfsQueue.isNotEmpty()) {
                val (column, row) = bfsQueue.poll()

                // 범위 체크
                if (column !in 0..columnSize + 1 || row !in 0..rowSize + 1) continue

                // 이미 방문한 곳이거나, 벽이거나, 잠긴 문이라면 건너뛰기
                if (vis[column][row] || map[column][row] == '*' || map[column][row] in 'A'..'Z') continue

                vis[column][row] = true

                when (val key = map[column][row]) {
                    '$' -> { // 문서를 찾았을 때
                        documentCount++
                        map[column][row] = '.'
                    }
                    in 'a'..'z' -> { // 열쇠 찾았을 때
                        map[column][row] = '.'
                        if (!keys[key.code - 97]) { // 이미 갖고 있던 키가 아닐 때만
                            keys[key.code - 97] = true
                            setDoorOpenByKey(key) // 문 열어두기
                            // 잠긴 문이 열렸으므로 모든 경로를 다시 확인
                            vis = List(columnSize + 2) { MutableList(rowSize + 2) { false } }
                            bfsQueue = LinkedList()
                            bfsQueue.offer(listOf(column, row))
                            continue
                        }
                    }
                }

                repeat(4) { upperIndex ->
                    val upedColumn = column + columnUpper[upperIndex]
                    val upedRow = row + rowUpper[upperIndex]
                    bfsQueue.offer(listOf(upedColumn, upedRow))
                }
            }
        }

        // 시작점을 쉽게 (0, 0)로 잡기 위해 모든 테두리에 빈 공간을 더해줌
        map[0] = MutableList(rowSize + 2) { '.' }
        for (columnIndex in 1..columnSize) {
            // ("." + br.readLine().trim() + ".").toCharArray() 그냥 이렇게 할 수도 있음
            val line = mutableListOf<Char>()
            val input = br.readLine()!!.toCharArray().asList()
            line.add('.')
            line.addAll(input)
            line.add('.')
            map[columnIndex] = line
        }
        map[columnSize + 1] = MutableList(rowSize + 2) { '.' }

        // 맨 처음부터 갖고 시작하는 열쇠들 (소문자로 주어짐)
        val defaultKeys = br.readLine()!!.toCharArray()
        if (defaultKeys[0] != '0') { // 열쇠가 있을 때
            defaultKeys.forEach { key ->
                keys[(key - 97).code] = true
                setDoorOpenByKey(key) // 문 열어두기
            }
        }

        bfs()
        /*bw.write(map.joinToString("\n") { it.joinToString(" ") } )
        bw.write("\n\n\n")*/
        bw.write("$documentCount\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
