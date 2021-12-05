import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val alphabetsGroup = br.readLine()!!.uppercase().split("").filterNot { it.isBlank() }.groupBy { it }
    val manyUsingAlphabetCount = alphabetsGroup.maxOf { it.value.size }

    val manyUsingAlphabet = if (alphabetsGroup.count { it.value.size == manyUsingAlphabetCount } != 1) {
        "?"
    } else {
        alphabetsGroup.filterValues { it.count() == manyUsingAlphabetCount }.keys.first()
    }

    bw.write("$manyUsingAlphabet\n")
    br.close()
    bw.flush()
    bw.close()
}
