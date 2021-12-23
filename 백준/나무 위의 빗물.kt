import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// 물을 가지고 있으며, 자식 정점이 있다면 자식 정점 중 하나를 골라 물을 1 준다. 자식 정점이 여러 개라면 동일한 확률로 그 중 하나를 고른다.
// -> 리프 노드일 때만 물을 가지고 있을 수 있음 (자식 노드가 있다면, 자식 노드에게 계속 물을 넘김)
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (nodeSize, waterSize) = br.readLine()!!.split(" ").map { it.toInt() }
    val forEachNodeCount = MutableList(nodeSize + 1) { 0 }
    repeat(nodeSize - 1) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        forEachNodeCount[node]++
        forEachNodeCount[node2]++
    }

    var leafNodeCount = 0.toDouble()
    for (i in 2..nodeSize) {
        if (forEachNodeCount[i] == 1) leafNodeCount++
    }
    bw.write("${waterSize / leafNodeCount}")

    br.close()
    bw.flush()
    bw.close()
}
