import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private const val MAX_POW_SIZE = 30 // 제곱은 최대 30번을 넘지 않음

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val nodesCount = br.readLine()!!.toInt()
    val nodes = MutableList(nodesCount + 1) { mutableListOf<Int>() }
    val depths = MutableList(nodesCount + 1) { 0 } // 노드의 깊이 (LCA 구하려면 노드 깊이 맞춰야 함)
    val parents =
        MutableList(nodesCount + 1) { MutableList(MAX_POW_SIZE + 1) { 0 } } // parents[node][pow] -> node의 2^pow 번째 위에 있는 노드

    repeat(nodesCount - 1) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    fun dfs(nowNode: Int = 1, parentNode: Int = 0) {
        depths[nowNode] = depths[parentNode] + 1 // nowNode의 깊이는 부모 노드의 깊이 + 1 임
        parents[nowNode][0] = parentNode // 2^0 번째 위에 있는 노드는 항상 부모 노드임

        for (newNode in nodes[nowNode]) {
            if (parentNode != newNode) {
                dfs(nowNode = newNode, parentNode = nowNode)
            }
        }
    }

    fun initSparseTable() {
        for (pow in 1 until MAX_POW_SIZE) {
            for (node in 1..nodesCount) {
                // 2^(i-1) + 2^(i-1) = 2^(i) 가 성립하기 때문에
                // node에서 2^(i-1)번 위로 올라가고, 거기에서 또 2^(i-1)번 위로 올라가면 node에서 2^(i)번 올라간 것과 같음
                parents[node][pow] = parents[parents[node][pow - 1]][pow - 1]
            }
        }
    }

    // Lowest Common Ancestor (최소 공통 조상)
    fun lca(_node: Int, _node2: Int): Int {
        var node = _node
        var node2 = _node2
        // node의 깊이가 node2의 깊이보다 깊을 때, node2의 깊이가 node의 깊이보다 깊을 때 둘 다 고려해주기 번거로우므로
        // **node2의 깊이가 node의 깊이보다 더 깊게 구해질 수 있게 서로 swap 해줌**
        if (depths[node] > depths[node2]) {
            node = node2.also { node2 = node }
        }
        // node와 node2의 깊이가 같게 맞춰줌
        // 이 부분 반복문은 순서(downTo 인지 아닌지) 상관 없음
        for (pow in MAX_POW_SIZE downTo 0) {
            val node2Parent = parents[node2][pow]
            // node의 깊이보다 node2의 깊이가 더 깊게 구해지게 만들어 줬으므로,
            // node2의 pow 번째 위로 올라가면서 깊이가 node의 깊이와 같게 만들어 줘야함.
            if (depths[node] <= depths[node2Parent]) {
                node2 = node2Parent
            }
        }
        if (node == node2) { // 만약 2개의 노드가 같다면, 둘 중 아무 노드나 lca임
            return node
        }
        // 위에서 node와, node2의 깊이를 갚게 맞춰줬으므로
        // node와, node2의 pow 번째 위를 찾아보면서 공통 조상을 찾아야 함
        // pow가 높을 수록 위에 있는 노드임. 즉 pow가 낮아질수록 점점 깊어짐
        // 우리는 가장 가까운 조상을 찾아야 하니, 부모가 같은 노드들 중에 **제일 깊은** 노드를 찾아줌
        for (pow in MAX_POW_SIZE downTo 0) {
            val nodeParent = parents[node][pow]
            val node2Parent = parents[node2][pow]
            // 만약 nodeParent와 node2Parent가 다르면, node와 node2 같이 위에 있는 노드로 바꿔줌
            if (nodeParent != node2Parent) {
                node = nodeParent
                node2 = node2Parent
            }
        }
        // 마지막엔 nodeParent와 node2Parent가 같은 상태이므로, 아무 노드의 바로 위에 있는 노드가 lca임
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
