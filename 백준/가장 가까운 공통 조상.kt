import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val caseCount = br.readLine()!!.toInt()
    repeat(caseCount) {
        val nodesCount = br.readLine()!!.toInt()
        val visited = MutableList(nodesCount + 1) { false }
        val parents = MutableList(nodesCount + 1) { 0 }

        repeat(nodesCount - 1) {
            val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
            parents[node2] = node
        }

        fun lca(_node: Int, _node2: Int): Int {
            var node = _node
            var node2 = _node2

            while (node > 0) { // node의 부모들을 따라 올라가면서, 방문 처리 해줌
                visited[node] = true
                node = parents[node]
            }
            while (node2 > 0) { // node2의 부모들을 따라 올라가다가, node의 부모랑 겹치는 노드가 있다면 해당 노드가 LCA
                if (visited[node2]) {
                    return node2
                }
                node2 = parents[node2]
            }

            throw Exception("LCA가 없음!")
        }

        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        bw.write("${lca(node, node2)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
