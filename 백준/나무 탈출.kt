import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val nodeCount = br.readLine()!!.toInt()
    val nodes = MutableList(nodeCount + 1) { mutableListOf<Int>() }
    var nodesDepthSum = 0

    repeat(nodeCount - 1) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    // 모든 (리프 노드에서 루트 노드까지의 길이)가 짝수라면 패배하고, 홀수라면 이김
    fun dfs(node: Int = 1, parent: Int = 0, childrenCount: Int = 0) {
        for (newNode in nodes[node]) {
            if (newNode != parent) {
                dfs(node = newNode, parent = node, childrenCount = childrenCount + 1)
            }
        }

        if (nodes[node].size == 1) { // 리프 노드일 때
            nodesDepthSum += childrenCount
        }
    }

    dfs()
    bw.write(if (nodesDepthSum % 2 == 0) "No" else "Yes")

    br.close()
    bw.flush()
    bw.close()
}
