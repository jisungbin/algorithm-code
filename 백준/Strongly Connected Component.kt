import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.SortedSet
import java.util.Stack
import kotlin.math.min

private const val DefaultNodeVisitValue = false
private const val DefaultNodeIdValue = -1

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (_nodeSize, edgeCount) = br.readLine()!!.split(" ").map { it.toInt() }
    val nodeSize = _nodeSize + 1 // 노드 index 시작은 1 부터라 size + 1 처리

    val nodes = MutableList(nodeSize) { mutableListOf<Int>() } // 전체 노드들
    val sccs = sortedSetOf<SortedSet<Int>>(compareBy { it.first() }) // scc 노드들이 담길 배열
    val dfsStack = Stack<Int>() // 타잔 알고리즘 스택
    var dfsCount = 0 // dfs 재귀 휫수 -> 각 노드들의 키가 될 값
    val nodesId = MutableList(nodeSize) { DefaultNodeIdValue } // 각 노드별 키
    val nodesVisit = MutableList(nodeSize) { DefaultNodeVisitValue } // scc 중복 방지를 위한 노드 방문 여부 배열
    val nodesParentValue =
        MutableList(nodeSize) { DefaultNodeIdValue } // 현재 노드에서 dfs로 갈 수 있는 가장 작은 노드 -> scc가 성립하는 루트 노드 찾기

    repeat(edgeCount) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
    }

    fun dfs(nowNode: Int) { // 현재 노드 번호를 인자로 받음
        if (nodesId[nowNode] != DefaultNodeIdValue) return // 이미 예전에 dfs를 돌렸던 노드면 건너뛰기 (중복 dfs 방지)

        dfsStack.add(nowNode)
        nodesId[nowNode] = ++dfsCount
        nodesParentValue[nowNode] = dfsCount // 임시값 할당
        nodesVisit[nowNode] = true // 방문한 노드에 체크

        for (newNode in nodes[nowNode]) {
            if (nodesId[newNode] == DefaultNodeIdValue) { // 노드 키가 정해지지 않았다 -> 아직 dfs를 돌리지 않았다
                dfs(newNode) // -> 이 재귀에서 scc가 성립한다면, nodesVisit[newNode]를 false로 바꿔 중복 scc를 방지시킴
                nodesParentValue[nowNode] = min(nodesParentValue[nowNode], nodesParentValue[newNode]) // 더 작은 값이 루트임
            } else if (nodesVisit[newNode]) { // 방문했던 노드에 다시 들어오면 최초 dfs를 돌린 nowNode에 있어서 dfs가 끝남 -> scc 체크
                // 방문했던 노드에 다시 들어오면 해당 노드는 루트 노드 역할을 하고 있음 -> 루트 노드 키 비교
                nodesParentValue[nowNode] = min(nodesParentValue[nowNode], nodesId[newNode]) // 더 작은 값이 부모임
            }
        }

        if (nodesId[nowNode] == nodesParentValue[nowNode]) { // 만약 현재 노드와, 현재 노드의 부모 노드와 값이 같다면 scc가 성립함
            val scc = sortedSetOf<Int>()
            while (dfsStack.isNotEmpty()) {
                val node = dfsStack.pop()
                scc.add(node)
                nodesVisit[node] = false // 만약 scc에 포함 됐다면, 중복해서 scc에 포함시키면 안되므로 방문한 노드에서 제거
                if (nowNode == node) break // 만약 내 노드가 pop 됐다면 scc 범위 끝났으므로 반복문 끝냄
            }
            sccs.add(scc) // scc 배열에 추가
        }
    }

    for (i in 1 until nodeSize) { // 노드 index는 1 부터 시작
        dfs(i)
    }

    bw.write("${sccs.size}\n")
    for (scc in sccs) {
        bw.write("${scc.joinToString(" ")} -1\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
