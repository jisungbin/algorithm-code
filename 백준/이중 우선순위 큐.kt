import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val queueCount = br.readLine()!!.toInt()
    repeat(queueCount) {
        val queue = TreeMap<Int, Int>()
        repeat(br.readLine()!!.toInt()) {
            val (command, input) = br.readLine()!!.split(" ")
            if (command == "I") {
                queue[input.toInt()] = queue.getOrDefault(input.toInt(), 0) + 1
            } else { // D
                if (queue.isNotEmpty()) {
                    val key = if (input == "1") queue.lastKey() else queue.firstKey()
                    if (queue.put(key, queue[key]!! - 1) == 1) {
                        queue.remove(key)
                    }
                }
            }
        }
        if (queue.isEmpty()) {
            bw.write("EMPTY\n")
        } else {
            bw.write("${queue.lastKey()} ${queue.firstKey()}\n")
        }
    }

    br.close()
    bw.flush()
    bw.close()
}
