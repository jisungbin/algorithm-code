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

private class Tree {
    private var root: TypedNode? = null

    fun add(root: TypedNode? = this.root, key: KeyType) {
        if (root == null) {
            this.root = Node(key = key)
        } else {
            if (key > root.key) { // 오른쪽에 넣기
                if (root.right == null) {
                    root.right = Node(key = key)
                } else {
                    add(root = root.right, key = key)
                }
            } else if (key < root.key) { // 왼쪽에 넣기
                if (root.left == null) {
                    root.left = Node(key = key)
                } else {
                    add(root = root.left, key = key)
                }
            }
        }
    }

    // 전위 순회
    fun preOrder(root: TypedNode = this.root!!, action: (key: KeyType) -> Unit) {
        action(root.key)
        root.left?.let { left -> preOrder(left, action) }
        root.right?.let { right -> preOrder(right, action) }
    }

    // 중위 순회
    fun inOrder(root: TypedNode = this.root!!, action: (key: KeyType) -> Unit) {
        root.left?.let { left -> inOrder(left, action) }
        action(root.key)
        root.right?.let { right -> inOrder(right, action) }
    }

    // 후위 순회
    fun postOrder(root: TypedNode = this.root!!, action: (key: KeyType) -> Unit) {
        root.left?.let { left -> postOrder(left, action) }
        root.right?.let { right -> postOrder(right, action) }
        action(root.key)
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val tree = Tree()
    val postOrderKeys = mutableListOf<Int>()

    while (true) {
        val key = br.readLine()?.toInt() ?: break
        tree.add(key = key)
    }

    tree.postOrder { key -> postOrderKeys.add(key) }

    bw.write(postOrderKeys.joinToString("\n"))
    br.close()
    bw.flush()
    bw.close()
}
