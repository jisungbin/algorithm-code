import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val maxLevel = br.readLine()!!.toInt()
    val nodes = br.readLine()!!.split(" ")

    val levelsForEachNodes = MutableList(maxLevel) { mutableListOf<String>() }
    getNodesForEachLevel(
        nodes = nodes,
        levelsForEachNodes = levelsForEachNodes,
        maxLevel = maxLevel
    )

    bw.write(levelsForEachNodes.joinToString("\n") { it.joinToString(" ") })

    br.close()
    bw.flush()
    bw.close()
}

private fun getNodesForEachLevel(
    nodes: List<String>,
    levelsForEachNodes: MutableList<MutableList<String>>,
    level: Int = 0,
    maxLevel: Int,
    startNodeIndex: Int = 0,
    endNodeIndex: Int = nodes.size - 1
) {
    if (level == maxLevel) return
    val centerIndex = (startNodeIndex + endNodeIndex) / 2
    levelsForEachNodes[level].add(nodes[centerIndex])

    // 왼쪽 노드를 먼저 검사해야 함
    getNodesForEachLevel(
        nodes = nodes,
        levelsForEachNodes = levelsForEachNodes,
        level = level + 1,
        maxLevel = maxLevel,
        startNodeIndex = startNodeIndex,
        endNodeIndex = centerIndex - 1
    )
    getNodesForEachLevel(
        nodes = nodes,
        levelsForEachNodes = levelsForEachNodes,
        level = level + 1,
        maxLevel = maxLevel,
        startNodeIndex = centerIndex + 1,
        endNodeIndex = endNodeIndex
    )
}
