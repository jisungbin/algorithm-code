import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private data class Node(val key: Int, val depth: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var depth: Int

    val (nodeCount, queryCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val nodes = MutableList(nodeCount + 1) { mutableListOf<Node>() }

    repeat(nodeCount - 1) {
        val (node, node2, _depth) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(Node(key = node2, depth = _depth))
        nodes[node2].add(Node(key = node, depth = _depth))
    }

    fun dfs(node: Int, targetNode: Int, parent: Int = 0, _depth: Int = 0) {
        for (newNode in nodes[node]) {
            if (newNode.key != parent) {
                if (newNode.key == targetNode) {
                    depth = _depth + newNode.depth
                    return
                }
                dfs(node = newNode.key, targetNode = targetNode, parent = node, _depth = _depth + newNode.depth)
            }
        }
    }

    repeat(queryCount) {
        val (node, targetNode) = br.readLine()!!.split(" ").map { it.toInt() }
        depth = 0
        dfs(node = node, targetNode = targetNode)
        bw.write("$depth\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
