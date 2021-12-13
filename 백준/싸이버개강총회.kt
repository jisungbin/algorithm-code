import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val peoples = mutableMapOf<String, Int>()
private var passPeolplesCount = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (startTime, finishTime, closeTime) = br.readLine().split(" ").map { timeToSecond(it) }
    while (true) {
        val input = br.readLine()?.split(" ") ?: break
        val chatTime = timeToSecond(input[0])
        val name = input[1]
        if (peoples[name] == null) { // 처음 온 사람이라면
            if (chatTime <= startTime) { // 시작시간 전이나 같이 왔다면 출석
                peoples[name] = 0
            }
        } else {
            if (chatTime in finishTime..closeTime) {
                passPeolplesCount++
                peoples.remove(name)
            }
        }
    }

    bw.write("$passPeolplesCount")

    br.close()
    bw.flush()
    bw.close()
}

private fun timeToSecond(time: String): Int {
    val (minute, second) = time.split(":").map { it.toInt() }
    return minute * 60 + second
}
