import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val computerCount = br.readLine()!!.toInt()
    val linkedComputerCount = br.readLine()!!.toInt()
    val linkedComputers = MutableList(computerCount + 1) { mutableListOf<Int>() }

    val vis = MutableList(computerCount + 1) { false }
    val bfsQueue: Queue<Int> = LinkedList()

    repeat(linkedComputerCount) {
        val (computer, computer2) = br.readLine()!!.split(" ").map { it.toInt() }
        linkedComputers[computer].add(computer2)
        linkedComputers[computer2].add(computer)
    }

    var trashComputerCount = 0
    bfsQueue.offer(1)
    vis[1] = true
    while (bfsQueue.isNotEmpty()) {
        val computer = bfsQueue.poll()
        for (linkedComputer in linkedComputers[computer]) {
            if (!vis[linkedComputer]) {
                vis[linkedComputer] = true
                bfsQueue.offer(linkedComputer)
                trashComputerCount++
            }
        }
    }

    bw.write("$trashComputerCount")

    br.close()
    bw.flush()
    bw.close()
}
