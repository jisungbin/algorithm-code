import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var stringsContainsCount = 0
    val (stringsCount, needSearchStringsCount) = br.readLine().split(" ").map { it.toInt() }
    val strings = hashMapOf<String, Boolean>()
    val needSearchStrings = mutableListOf<String>()

    repeat(stringsCount) {
        strings[br.readLine()] = true
    }

    repeat(needSearchStringsCount) {
        needSearchStrings.add(br.readLine())
    }

    for (i in needSearchStrings.indices) {
        if (strings[needSearchStrings[i]] != null) {
            stringsContainsCount++
        }
    }

    bw.write("$stringsContainsCount")
    br.close()
    bw.flush()
    bw.close()
}
