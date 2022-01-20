import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    // 이거 진짜 미친 싸이코문제;
    val n = br.readLine()!!.toInt()
    val size = 4 * n - 3
    val map = List(size) { MutableList(size) { " " } }

    fun draw(n: Int, index: Int) {
        if (n == 1) {
            map[index][index] = "*"
            return
        }
        val newSize = 4 * n - 3

        for (i in index until newSize + index) {
            map[index][i] = "*"
            map[index + newSize - 1][i] = "*"
            map[i][index] = "*"
            map[i][index + newSize - 1] = "*"
        }

        draw(n - 1, index + 2)
    }

    draw(n, 0)

    bw.write(map.joinToString("\n") { it.joinToString("") })

    br.close()
    bw.flush()
    bw.close()
}
