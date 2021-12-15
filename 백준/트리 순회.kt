import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private data class Node(
    var key: String,
    var left: Node? = null,
    var right: Node? = null
)

private class Tree {
    private var root: Node? = null

    fun add(node: Node) {
        // 루트 노드가 없다면 루트 노드 먼저 넣기
        if (root == null) {
            root = node
        } else {
            searchAndUpdate(root = root!!, newNode = node)
        }
    }

    private fun searchAndUpdate(root: Node, newNode: Node) {
        // 만약 현재 루트가 추가할 노드의 루트와 같다면
        if (root.key == newNode.key) {
            root.left = newNode.left
            root.right = newNode.right
        }
        // 만약 현재 루트가 추가할 노드의 루트와 같지 않다면, 루트의 좌우 탐색
        else {
            root.left?.let { left -> searchAndUpdate(left, newNode) }
            root.right?.let { right -> searchAndUpdate(right, newNode) }
        }
    }

    // 전위 순회
    fun preOrder(root: Node = this.root!!, action: (key: String) -> Unit) {
        action(root.key)
        root.left?.let { left -> preOrder(left, action) }
        root.right?.let { right -> preOrder(right, action) }
    }

    // 중위 순회
    fun inOrder(root: Node = this.root!!, action: (key: String) -> Unit) {
        root.left?.let { left -> inOrder(left, action) }
        action(root.key)
        root.right?.let { right -> inOrder(right, action) }
    }

    // 후위 순회
    fun postOrder(root: Node = this.root!!, action: (key: String) -> Unit) {
        root.left?.let { left -> postOrder(left, action) }
        root.right?.let { right -> postOrder(right, action) }
        action(root.key)
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val tree = Tree()

    repeat(br.readLine()!!.toInt()) {
        val (root, _left, _right) = br.readLine()!!.split(" ")
        val left = if (_left == ".") null else _left
        val right = if (_right == ".") null else _right

        val node = Node(key = root)
        if (left != null) node.left = Node(key = left)
        if (right != null) node.right = Node(key = right)

        tree.add(node)
    }

    var treeNodesKeys = mutableListOf<String>()
    tree.preOrder { key ->
        treeNodesKeys.add(key)
    }
    bw.write(treeNodesKeys.joinToString(separator = "", postfix = "\n"))
    treeNodesKeys = mutableListOf()

    tree.inOrder { key ->
        treeNodesKeys.add(key)
    }
    bw.write(treeNodesKeys.joinToString(separator = "", postfix = "\n"))
    treeNodesKeys = mutableListOf()

    tree.postOrder { key ->
        treeNodesKeys.add(key)
    }
    bw.write(treeNodesKeys.joinToString(separator = "", postfix = "\n"))

    br.close()
    bw.flush()
    bw.close()
}
