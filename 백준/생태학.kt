import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var treesCount = 0
    val treesMap = mutableMapOf<String, Int>()

    while (true) {
        val tree = br.readLine()
        if (tree == null || tree.isBlank()) break
        treesMap[tree] = treesMap.getOrDefault(tree, 0) + 1
        treesCount++
    }

    val treeNamesSorted = treesMap.keys.sorted()

    for (i in treeNamesSorted.indices) {
        val treeName = treeNamesSorted[i]
        val percentage = treesMap[treeName]!! * 100.0f / treesCount
        bw.write("$treeName ${String.format("%.4f", percentage)}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
