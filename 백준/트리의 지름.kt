import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private data class Node(val key: Int, val weight: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val nodeSize = br.readLine()!!.toInt()
    val nodes = MutableList(nodeSize + 1) { mutableListOf<Node>() }

    var treeWeight = 0
    var deepNode = 0

    repeat(nodeSize - 1) {
        val (node, node2, weight) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(Node(key = node2, weight = weight))
        nodes[node2].add(Node(key = node, weight = weight))
    }

    fun dfs(node: Int = 1, parent: Int = 0, weight: Int = 0) {
        if (treeWeight < weight) {
            treeWeight = weight // 트리 지름 더 큰 걸로 업데이트 (최대 지름으로 갱신)
            deepNode = node // 지름이 더 깊어지는 노드가, 제일 깊은 노드임
        }

        for (newNode in nodes[node]) {
            if (newNode.key != parent) {
                dfs(node = newNode.key, parent = node, weight = newNode.weight + weight)
            }
        }
    }

    dfs() // 제일 깊은 노드를 찾음
    dfs(node = deepNode) // 위에서 찾은 제일 깊은 노드로, 트리의 지름을 측정함

    bw.write("$treeWeight")

    br.close()
    bw.flush()
    bw.close()
}
