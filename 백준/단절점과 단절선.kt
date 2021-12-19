import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private const val DefaultNodeIdValue = -1

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val nodeSize = br.readLine()!!.toInt()
    val nodes = MutableList(nodeSize + 1) { mutableListOf<Int>() } // 트리는 index 시작이 1 이므로 + 1

    repeat(nodeSize - 1) {
        val (node, node2) = br.readLine()!!.split(" ").map { it.toInt() }
        nodes[node].add(node2)
        nodes[node2].add(node)
    }

    val questionCount = br.readLine()!!.toInt()
    repeat(questionCount) {
        val (type, index) = br.readLine()!!.split(" ").map { it.toInt() }
        when (type) {
            1 -> { // index가 단절점인지 -> 트리는 사이클이 없기 때문에 연결된 간선이 2개 이상이면 단절점임
                bw.write(if (nodes[index].size > 1) "yes" else "no")
            }
            2 -> { // 입력에서 주어지는 index - 1 번째 간선이 단절선인지 -> 트리는 사이클이 없기 때문에 모든 선이 단절선임
                bw.write("yes")
            }
        }
        bw.write("\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
