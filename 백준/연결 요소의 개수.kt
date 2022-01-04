import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

@Suppress("BlockingMethodInNonBlockingContext")
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (nodeCount, edgeCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val vis = MutableList(nodeCount + 1) { false }
    val nodes = MutableList(nodeCount + 1) { mutableListOf<Int>() }

    repeat(edgeCount) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    fun dfs(node: Int) {
        vis[node] = true

        for (newNode in nodes[node]) {
            if (!vis[newNode]) dfs(newNode)
        }
    }

    var count = 0
    for (i in 1..nodeCount) {
        if (!vis[i]) {
            dfs(i)
            count++
        }
    }
    bw.write("$count")

    br.close()
    bw.flush()
    bw.close()
}
