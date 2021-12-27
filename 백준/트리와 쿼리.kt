import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (nodeCount, rootNode, queryCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val nodes = MutableList(nodeCount + 1) { mutableListOf<Int>() }
    val forEachNodesSubTreesNodeCount = MutableList(nodeCount + 1) { 1 }

    repeat(nodeCount - 1) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    fun dfs(node: Int = rootNode, parent: Int = 0): Int {
        for (newNode in nodes[node]) {
            if (newNode != parent) {
                forEachNodesSubTreesNodeCount[node] += dfs(node = newNode, parent = node)
            }
        }
        return forEachNodesSubTreesNodeCount[node]
    }

    dfs()
    repeat(queryCount) {
        val subTreeSplitNode = br.readLine()!!.toInt()
        bw.write("${forEachNodesSubTreesNodeCount[subTreeSplitNode]}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
