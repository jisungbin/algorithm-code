import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var binggoCount = 0
    val binggo = mutableMapOf<Int, Boolean>()
    val numberLocateMap = mutableMapOf<Int, Pair<Int, Int>>()
    val map = List(5) { columnIndex ->
        br.readLine()!!.split(" ").mapIndexed { rowIndex, _number ->
            val number = _number.toInt()
            binggo[number] = false
            numberLocateMap[number] = columnIndex to rowIndex
            number
        }
    }
    val input = List(5) { br.readLine()!!.split(" ").map(String::toInt) }.flatten()

    var checkArrow1AlreadyBinggo = false
    var checkArrow2AlreadyBinggo = false

    fun checkBingGo(number: Int): Boolean {
        var column = numberLocateMap[number]!!.first
        var row = numberLocateMap[number]!!.second
        binggo[number] = true

        // column 빙고 확인
        run checkColumn@{
            repeat(5) { newColumn ->
                val newNumber = map[newColumn][row]
                // 체크되지 않은 빙고 -> 더 볼 필요가 없음
                if (!binggo[newNumber]!!) {
                    return@checkColumn
                }
            }
            // 여끼가지 왔으면 현재 column은 다 체크된 빙고들
            binggoCount++
        }

        // row 빙고 확인
        run checkRow@{
            repeat(5) { newRow ->
                val newNumber = map[column][newRow]
                // 체크되지 않은 빙고 -> 더 볼 필요가 없음
                if (!binggo[newNumber]!!) {
                    return@checkRow
                }
            }
            // 여끼가지 왔으면 현재 row는 다 체크된 빙고들
            binggoCount++
        }

        // 대각선 빙고 확인
        if (!checkArrow1AlreadyBinggo) {
            column = 5
            row = -1

            run checkArrow1@{
                while (--column >= 0 && ++row < 5) {
                    val newNumber = map[column][row]
                    // bw.write("number: $number, column: $column, row: $row, newNumber: $newNumber, binggo: ${binggo[newNumber]!!}\n\n")
                    // 체크되지 않은 빙고 -> 더 볼 필요가 없음
                    if (!binggo[newNumber]!!) {
                        return@checkArrow1
                    }
                }
                // 여끼가지 왔으면 현재 1 대각선은 다 체크된 빙고들
                binggoCount++
                checkArrow1AlreadyBinggo = true
            }
        }

        if (!checkArrow2AlreadyBinggo) {
            column = -1
            row = -1

            // 2) column +1, row +1 / column -1, row -1
            run checkArrow2@{
                while (++column < 5 && ++row < 5) {
                    val newNumber = map[column][row]
                    // 체크되지 않은 빙고 -> 더 볼 필요가 없음
                    if (!binggo[newNumber]!!) {
                        return@checkArrow2
                    }
                }

                // 여끼가지 왔으면 현재 2 대각선은 다 체크된 빙고들
                binggoCount++
                checkArrow2AlreadyBinggo = true
            }
        }
        return binggoCount >= 3
    }

    run loop@{
        input.forEachIndexed { index, number ->
            if (checkBingGo(number)) {
                bw.write("${index + 1}")
                return@loop
            }
        }
    }

    br.close()
    bw.flush()
    bw.close()
}
