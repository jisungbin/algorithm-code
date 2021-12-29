import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (nodeCount, rootNode, power) = br.readLine()!!.split(" ").map { it.toInt() }
    val nodes = List(nodeCount + 1) { mutableListOf<Int>() }
    val childrenNodeCount = MutableList(nodeCount + 1) { 0 }

    repeat(nodeCount - 1) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    fun dfs(node: Int = rootNode, parent: Int = 0): Int {
        if (node != rootNode && nodes[node].size == 1) { // 리프 노드 일 때
            childrenNodeCount[node] = 0
            return 0
        }
        for (newNode in nodes[node]) {
            if (newNode != parent) {
                childrenNodeCount[node] = max(childrenNodeCount[node], dfs(node = newNode, parent = node) + 1)
            }
        }
        return childrenNodeCount[node]
    }

    dfs()

    var length = 0
    for (i in 1 .. nodeCount) {
        if (i != rootNode && childrenNodeCount[i] >= power) {
            length++
        }
    }

    bw.write("${length * 2}")

    br.close()
    bw.flush()
    bw.close()
}
