import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private const val MAX_POW_SIZE = 30

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val nodesCount = br.readLine()!!.toInt()
    val nodes = MutableList(nodesCount + 1) { mutableListOf<Int>() }
    val depths = MutableList(nodesCount + 1) { 0 }
    val parents = MutableList(nodesCount + 1) { MutableList(MAX_POW_SIZE + 1) { 0 } }

    repeat(nodesCount - 1) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    fun dfs(nowNode: Int = 1, parentNode: Int = 0) {
        depths[nowNode] = depths[parentNode] + 1
        parents[nowNode][0] = parentNode

        for (newNode in nodes[nowNode]) {
            if (parentNode != newNode) {
                dfs(nowNode = newNode, parentNode = nowNode)
            }
        }
    }

    fun initSparseTable() {
        for (pow in 1 until MAX_POW_SIZE) {
            for (node in 1..nodesCount) {
                parents[node][pow] = parents[parents[node][pow - 1]][pow - 1]
            }
        }
    }

    fun lca(_node: Int, _node2: Int): Int {
        var node = _node
        var node2 = _node2
        if (depths[node] > depths[node2]) {
            val temp = node
            node = node2
            node2 = temp
        }
        for (pow in MAX_POW_SIZE downTo 0) {
            val node2Parent = parents[node2][pow]
            if (depths[node] <= depths[node2Parent]) {
                node2 = node2Parent
            }
        }
        if (node == node2) {
            return node
        }
        for (pow in MAX_POW_SIZE downTo 0) {
            val nodeParent = parents[node][pow]
            val node2Parent = parents[node2][pow]
            if (nodeParent != node2Parent) {
                node = nodeParent
                node2 = node2Parent
            }
        }
        return parents[node][0]
    }

    dfs()
    initSparseTable()

    val lcaCount = br.readLine()!!.toInt()
    repeat(lcaCount) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        bw.write("${lca(node, node2)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
