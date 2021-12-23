import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val nodeSize = br.readLine()!!.toInt()
    val inOrderNodes = br.readLine()!!.split(" ") // 중위 순위에서 구한 로트 노드로, 왼쪽 서브 트리와 오른쪽 서브 트리를 구분할 수 있음
    val postOrderNodes = br.readLine()!!.split(" ") // 후위 순위는 항상 맨 뒤가 루트 노드가 됨
    val inOrderNodesIndices = mutableMapOf<String, Int>() // indexOf 로 바로 구할경우 시간복잡도가 O(N)이 됨
    for (i in 0 until nodeSize) {
        inOrderNodesIndices[inOrderNodes[i]] = i // 시간복잡도 O(1) 만에 index를 구하기 위해 미리 넣어둠
    }

    fun preOrder(
        inOrderStartIndex: Int = 0,
        inOrderEndIndex: Int = nodeSize - 1,
        postOrderStartIndex: Int = 0,
        postOrderEndIndex: Int = nodeSize - 1
    ) {
        if (inOrderStartIndex > inOrderEndIndex || postOrderStartIndex > postOrderEndIndex) return
        val root = postOrderNodes[postOrderEndIndex] // 후위 순위는 항상 맨 뒤가 루트 노드가 됨
        val rootIndex = inOrderNodesIndices[root]!!
        val leftNodesCount = rootIndex - inOrderStartIndex // 루트 노드에서 왼쪽 서브 트리에 있는 노드들의 개수를 구함

        bw.write("$root ")
        preOrder( // 왼쪽 서브 트리
            inOrderStartIndex = inOrderStartIndex,
            inOrderEndIndex = rootIndex - 1,
            postOrderStartIndex = postOrderStartIndex,
            postOrderEndIndex = postOrderStartIndex + leftNodesCount - 1
        )
        preOrder( // 오른쪽 서브 트리
            inOrderStartIndex = rootIndex + 1,
            inOrderEndIndex = inOrderEndIndex,
            postOrderStartIndex = postOrderStartIndex + leftNodesCount,
            postOrderEndIndex = postOrderEndIndex - 1
        )
    }

    preOrder()
    br.close()
    bw.flush()
    bw.close()
}
