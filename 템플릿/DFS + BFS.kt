import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (nodeCount, edgeCount, rootNode) = br.readLine()!!.split(" ").map { it.toInt() }
    val nodes = List(nodeCount + 1) { mutableListOf<Int>() }
    var vis = MutableList(nodeCount + 1) { false }
    val bfsQueue: Queue<Int> = LinkedList()

    repeat(edgeCount) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    fun dfs(node: Int = rootNode) {
        vis[node] = true
        bw.write("$node ")
        for (newNode in nodes[node].sorted()) {
            if (!vis[newNode]) {
                dfs(node = newNode)
            }
        }
    }

    fun bfs(node: Int = rootNode) {
        bfsQueue.offer(node)
        vis[node] = true
        while (bfsQueue.isNotEmpty()) {
            val frontNode = bfsQueue.poll()
            bw.write("$frontNode ")
            for (newNode in nodes[frontNode].sorted()) {
                if (!vis[newNode]) {
                    bfsQueue.offer(newNode)
                    vis[newNode] = true
                }
            }
        }
    }

    dfs()
    bw.write("\n")
    vis = MutableList(nodeCount + 1) { false }
    bfs()

    br.close()
    bw.flush()
    bw.close()
}
