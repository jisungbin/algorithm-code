import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private const val DefaultMapValue = -1

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine()!!.toInt()
    val findN = br.readLine()!!.toInt()
    val findNLocate = mutableListOf<Int>()

    var number = N * N + 1
    val map = List(N) { MutableList(N) { DefaultMapValue } }

    var step = 1
    var columnIndex = 0
    var rowIndex = 0

    fun checkFindNAndSetNewNumberToMap() {
        map[columnIndex][rowIndex] = --number
        if (map[columnIndex][rowIndex] == findN) {
            findNLocate.addAll(listOf(columnIndex + 1, rowIndex + 1))
        }
    }

    repeat(N * N) {
        // 1. columnIndex를 +1 함 -> 만약 (N-1)번째 index라 범위 초과면 2번
        // 2. rowIndex를 + 1 함 -> 만약 (N-1)번째 index라 범위 초과면 3번
        // 3. columnIndex가 (N-1)번째 index 이므로 column - 1 함 -> 만약 0번째 index면 4번
        // 4. columnIndex가 0, rowIndex가 (N-1)번째 이므로 row - 1 함 -> 만약 이미 값이 들어가 있으면 해당 위치에서 1번 부터 반복
        checkFindNAndSetNewNumberToMap()
        when (step) {
            1 -> {
                if (columnIndex != N - 1 && map[columnIndex + 1][rowIndex] == DefaultMapValue) { // 마지막 column이 아니고, 다음 column을 방문하지 않았을 때
                    columnIndex++
                } else {
                    rowIndex++
                    step = 2
                }
            }
            2 -> {
                if (rowIndex != N - 1 && map[columnIndex][rowIndex + 1] == DefaultMapValue) { // 마지막 row가 아니고, 다음 row를 방문하지 않았을 때
                    rowIndex++
                } else {
                    columnIndex--
                    step = 3
                }
            }
            3 -> {
                if (columnIndex != 0 && map[columnIndex - 1][rowIndex] == DefaultMapValue) { // 첫 번째 column이 아니고, 이전 column을 방문하지 않았을 때
                    columnIndex--
                } else {
                    rowIndex--
                    step = 4
                }
            }
            4 -> {
                if (rowIndex != 0 && map[columnIndex][rowIndex - 1] == DefaultMapValue) { // 첫 번째 row가 아니고, 이전 row를 방문하지 않았을 때
                    rowIndex--
                } else {
                    columnIndex++
                    step = 1
                }
            }
        }
    }

    bw.write(map.joinToString("\n") { it.joinToString(" ") })
    bw.write("\n${findNLocate.joinToString(" ")}")

    br.close()
    bw.flush()
    bw.close()
}
