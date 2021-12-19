import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

private const val DefaultNodeIdValue = -1

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (_nodeSize, edgeCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val nodeSize = _nodeSize + 1 // 노드 index 시작은 1 부터라 size + 1 처리

    val nodes = MutableList(nodeSize) { mutableListOf<Int>() } // 전체 노드들
    var dfsCount = 0 // dfs 재귀 휫수 -> 각 노드들의 키가 될 값
    val nodesId = MutableList(nodeSize) { DefaultNodeIdValue } // 각 노드별 키
    val nodesParentValue = MutableList(nodeSize) { DefaultNodeIdValue } // 노드의 부모
    val nodesHigherValue = MutableList(nodeSize) { DefaultNodeIdValue } // i를 루트로 하는 서브 트리에서 가장 위로 갈 수 있는 노드
    val articulationPoints = sortedSetOf<Int>() // 단절점

    repeat(edgeCount) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    fun dfs(nowNode: Int) { // 현재 노드 번호를 인자로 받음
        if (nodesId[nowNode] != DefaultNodeIdValue) return // 이미 예전에 dfs를 돌렸던 노드면 건너뛰기 (중복 dfs 방지)

        nodesId[nowNode] = ++dfsCount
        nodesHigherValue[nowNode] = dfsCount // 임시값 할당
        var childrenCount = 0 // 자식 수

        for (newNode in nodes[nowNode]) {
            if (newNode == nodesParentValue[nowNode]) continue

            if (nodesId[newNode] == DefaultNodeIdValue) {
                nodesParentValue[newNode] = nowNode
                childrenCount++
                dfs(newNode)

                if (nodesParentValue[nowNode] == DefaultNodeIdValue && childrenCount > 1) { // 자식이 2개 이상인 루트 노드일땐 무조건 단절점
                    articulationPoints.add(nowNode)
                } else if (nodesParentValue[nowNode] != DefaultNodeIdValue && nodesHigherValue[newNode] >= nodesId[nowNode]) { // 루트 노드가 아니고, 내 노드보다 더 먼저 나온 노드로 나 없이 갈 수 없다면 단절점임
                    articulationPoints.add(nowNode)
                }

                nodesHigherValue[nowNode] = min(nodesHigherValue[newNode], nodesHigherValue[nowNode])
            } else {
                nodesHigherValue[nowNode] = min(nodesHigherValue[nowNode], nodesId[newNode])
            }
        }
    }

    for (i in 1 until nodeSize) { // 노드 index는 1 부터 시작
        dfs(i)
    }

    bw.write("${articulationPoints.size}\n")
    bw.write(articulationPoints.joinToString(" "))

    br.close()
    bw.flush()
    bw.close()
}
