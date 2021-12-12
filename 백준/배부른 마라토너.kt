import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val namesMap = mutableMapOf<String, Int>()
    val nameCount = br.readLine()!!.toInt()

    repeat(nameCount) {
        val name = br.readLine()
        namesMap[name] = namesMap.getOrDefault(name, 0) + 1
    }

    repeat(nameCount - 1) {
        val name = br.readLine()
        if (namesMap[name]!! == 1) {
            namesMap.remove(name)
        } else {
            namesMap[name] = namesMap[name]!! - 1
        }
    }

    bw.write(namesMap.keys.toList().first())

    br.close()
    bw.flush()
    bw.close()
}
