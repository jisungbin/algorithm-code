import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.SortedMap

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val nodeCount = br.readLine()!!.toInt()
    val nodes = MutableList(nodeCount + 1) { mutableListOf<Int>() }
    val nodesParent = sortedMapOf<Int, Int>() // node - node 로 접근할 수 있게 map으로 설정 (이때 트리 번호에 따라 정렬돼야 하므로 SortedMap 사용)

    repeat(nodeCount - 1) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }

        // a 노드에서 b 노드로, b 노드에서 a 노드로 접근할 수 있어야 해서 양 쪽에 다 넣어줌
        nodes[node].add(node2)
        nodes[node2].add(node)
    }


    // 문제: 첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.
    // 이때 "부모 노드 번호를 2번 노드부터 순서대로 출력" 에서 2번 노드부터란,
    // 노드의 index가 2번 인 것 부터가 아니라 **노드의 값이 2인 노드**부터 임!

    dfs(nodes = nodes, nodesParent = nodesParent, nowNode = 1, parentNode = 0)
    bw.write(nodesParent.values.joinToString("\n"))

    br.close()
    bw.flush()
    bw.close()
}

private fun dfs(nodes: List<List<Int>>, nodesParent: SortedMap<Int, Int>, nowNode: Int, parentNode: Int) {
    // 노드의 시작은 1 이므로, 부모 노드가 0 일땐 부모 노드 저장에서 제외
    if (parentNode != 0) nodesParent[nowNode] = parentNode

    // 현재 노드에서 갈 수 있는 모든 노드들을 탐색 (DFS)
    for (newNode in nodes[nowNode]) {
        // 현재 노드에서 갈 수 있는 노드가 현재 노드의 부모 노드가 아니라면 해당 노드로 DFS 재실행 (중복 DFS 방지)
        if (newNode != parentNode) {
            dfs(
                nodes = nodes,
                nodesParent = nodesParent,
                nowNode = newNode, // 현재 노드를 현재 노드애서 갈 수 있는 새로운 노드로 교체
                parentNode = nowNode // 현재 노드애서 갈 수 있는 새로운 노드의 부모를 현재 노드로 교체
            )
        }
    }
}
