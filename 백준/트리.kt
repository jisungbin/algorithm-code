import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private typealias KeyType = Int
private typealias TypedNode = Node<KeyType>

private data class Node<T>(
    var key: T,
    var left: Node<T>? = null,
    var right: Node<T>? = null
)

private class Tree(nodeSize: Int) {
    private val nodes = MutableList(nodeSize) { mutableListOf<Int>() }
    private var key = 0
    private var ignoredKey = -1
    private var rootNodeKey = -1
    var leafNodeCount = 0
        private set

    fun generateKeyAndInsertToParent(parentKey: KeyType) {
        if (parentKey == -1) {
            rootNodeKey = key
        } else {
            nodes[parentKey].add(key)
            nodes[key].add(parentKey)
        }
        key++
    }

    fun generateKeyAndInsertAllToParent(parentKeys: List<KeyType>) {
        parentKeys.forEach(::generateKeyAndInsertToParent)
    }

    fun setDfsIgnoreParentKey(key: KeyType) {
        ignoredKey = key
    }

    var loopTurn = 0 // 디버깅용
    fun calcLeafNodeCountFromDFS(nowNode: KeyType = 0, parentNode: KeyType = -1) {
        if (ignoredKey == -1 || rootNodeKey == -1) throw Exception("ignoredKey($ignoredKey) 혹은 rootNodeKey($rootNodeKey) 초기화 필요")
        if (ignoredKey == rootNodeKey) return
        loopTurn++
        var isLeafNode = true
        for (newNode in nodes[nowNode]) {
            // 현재 노드에서 갈 수 있는 노드가 현재 노드의 부모 노드가 아니라면 해당 노드로 DFS 재실행 (중복 DFS 방지)
            if (newNode != parentNode && newNode != ignoredKey) { // 새로 dfs를 돌릴 노드가 무시한 노드가 아닐 때
                isLeafNode = false // 만약 dfs를 돌릴 방향이 있으면 자식노드가 있는거니 리프노드가 아님
                calcLeafNodeCountFromDFS(
                    nowNode = newNode, // 현재 노드를 현재 노드애서 갈 수 있는 새로운 노드로 교체
                    parentNode = nowNode // 현재 노드애서 갈 수 있는 새로운 노드의 부모를, 현재 노드로 교체
                )
            }
        }
        if (isLeafNode) leafNodeCount++
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val nodeSize = br.readLine()!!.toInt()
    val tree = Tree(nodeSize = nodeSize)

    val nodeParentKeys = br.readLine()!!.split(" ").map { it.toInt() }
    val dfsIgnoreKey = br.readLine()!!.toInt()

    tree.setDfsIgnoreParentKey(dfsIgnoreKey)
    tree.generateKeyAndInsertAllToParent(parentKeys = nodeParentKeys)
    tree.calcLeafNodeCountFromDFS()

    bw.write("${tree.leafNodeCount}")
    br.close()
    bw.flush()
    bw.close()
}
