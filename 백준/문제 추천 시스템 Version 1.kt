import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap

private enum class ProblemType {
    EASY, HARD
}

private val solvedProblemNumbers = mutableListOf<Int>()
private val problemsTree = TreeMap<Int, TreeMap<Int, Int>>() // 난이도 - <문제 번호, 아무값>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(br.readLine()!!.toInt()) {
        val (problemNumber, problemLevel) = br.readLine()!!.split(" ").map { it.toInt() }
        problemsTree[problemLevel] = problemsTree.getOrDefault(problemLevel, TreeMap<Int, Int>()).apply {
            put(problemNumber, 0)
        }
    }

    repeat(br.readLine()!!.toInt()) {
        val commands = br.readLine().split(" ")
        val command = commands[0]
        when (command) {
            "add" -> {
                val problemNumber = commands[1].toInt()
                val problemLevel = commands[2].toInt()
                problemsTree[problemLevel] = problemsTree.getOrDefault(problemLevel, TreeMap<Int, Int>()).apply {
                    put(problemNumber, 0)
                }
            }
            "solved" -> {
                val problemNumber = commands[1].toInt()
                solvedProblemNumbers.add(problemNumber)
            }
            "recommend" -> {
                val type = commands[1]
                val recommandProblemNumber = if (type == "1") { // 가장 어려운 문제
                    recommendProblemNumber(ProblemType.HARD)
                } else { // 가장 쉬운 문제
                    recommendProblemNumber(ProblemType.EASY)
                }
                bw.write("$recommandProblemNumber\n")
            }
        }
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun recommendProblemNumber(type: ProblemType): Int {
    val problemLevel = if (type == ProblemType.HARD) {
        problemsTree.lastKey()
    } else {
        problemsTree.firstKey()
    }
    val problems = problemsTree[problemLevel]!!
    if (problems.isEmpty()) {
        problemsTree.remove(problemLevel)
        return recommendProblemNumber(type)
    }
    val problemNumber = if (type == ProblemType.HARD) {
        problems.lastKey()
    } else {
        problems.firstKey()
    }
    return if (solvedProblemNumbers.contains(problemNumber)) { // 이미 풀린 문제
        problems.remove(problemNumber)
        solvedProblemNumbers.remove(problemNumber)
        recommendProblemNumber(type)
    } else {
        problemNumber
    }
}
