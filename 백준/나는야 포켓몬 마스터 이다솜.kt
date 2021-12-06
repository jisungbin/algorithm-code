import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (pocketmonCount, questionCount) = br.readLine()!!.split(" ").map { it.toInt() }

    val pocketmonsName = List(pocketmonCount) { br.readLine()!! }
    val pocketmonsNumber = (1..pocketmonsName.size).map { it.toString() }
    val questions = List(questionCount) { br.readLine()!! }

    val pocketmonsNameMap = pocketmonsName.zip(pocketmonsNumber).toMap()
    val pocketmonsNumberMap = pocketmonsNumber.zip(pocketmonsName).toMap()

    questions.fastForEach { question ->
        bw.write("${pocketmonsNameMap[question] ?: pocketmonsNumberMap[question]}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

inline fun <T> List<T>.fastForEach(action: (T) -> Unit) {
    for (index in indices) {
        action(get(index))
    }
}
