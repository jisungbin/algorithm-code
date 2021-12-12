import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeMap

private enum class ProblemLevelType {
    EASY, HARD
}

private enum class ProblemNumberType {
    SMALL, BIG
}

private data class ProblemInfo(val level: Int, val group: Int)

private val problems = mutableMapOf<Int, ProblemInfo>()
private val groupedProblemsTree = TreeMap<Int, TreeMap<Int, TreeMap<Int, Int>>>() // <분류 - <난이도 - <문제 번호, 아무값>>>
private val problemsTree = TreeMap<Int, TreeMap<Int, Int>>() // <난이도 - <문제 번호, 아무값>>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(br.readLine()!!.toInt()) {
        val (problemNumber, problemLevel, problemGroup) = br.readLine()!!.split(" ").map { it.toInt() }
        updateProblemsTree(
            problemNumber = problemNumber,
            problemLevel = problemLevel,
            problemGroup = problemGroup
        )
    }

    repeat(br.readLine()!!.toInt()) {
        val commands = br.readLine().split(" ")
        val command = commands[0]
        when (command) {
            "add" -> {
                val problemNumber = commands[1].toInt()
                val problemLevel = commands[2].toInt()
                val problemGroup = commands[3].toInt()
                updateProblemsTree(
                    problemNumber = problemNumber,
                    problemLevel = problemLevel,
                    problemGroup = problemGroup
                )
            }
            "solved" -> {
                // groupedProblemsTree = <분류 - <난이도 - <문제 번호, 아무값>>>
                // problemsTree = <난이도 - <문제 번호, 아무값>>
                val problemNumber = commands[1].toInt()
                val problemInfo = problems[problemNumber]!!
                groupedProblemsTree[problemInfo.group]!![problemInfo.level]!!.remove(problemNumber)
                problemsTree[problemInfo.level]!!.remove(problemNumber)
            }
            "recommend" -> {
                val problemGroup = commands[1].toInt()
                val problemLevelType = commands[2]
                val recommandProblemNumber = if (problemLevelType == "1") { // 가장 어려운 문제
                    recommendProblemNumber(group = problemGroup, problemLevelType = ProblemLevelType.HARD)
                } else { // 가장 쉬운 문제
                    recommendProblemNumber(group = problemGroup, problemLevelType = ProblemLevelType.EASY)
                }
                bw.write("$recommandProblemNumber\n")
            }
            "recommend2" -> {
                val problemLevelType = commands[1]
                val recommandProblemNumber = if (problemLevelType == "1") { // 가장 어려운 문제
                    recommend2ProblemNumber(problemLevelType = ProblemLevelType.HARD)
                } else { // 가장 쉬운 문제
                    recommend2ProblemNumber(problemLevelType = ProblemLevelType.EASY)
                }
                bw.write("$recommandProblemNumber\n")
            }
            "recommend3" -> {
                val problemNumberType = commands[1]
                val problemLevel = commands[2].toInt()
                val recommandProblemNumber = if (problemNumberType == "1") { // 난이도가 problemLevel 보다 크거나 같은 문제
                    recommend3ProblemNumber(level = problemLevel, problemNumberType = ProblemNumberType.BIG)
                } else { // 난이도가 problemLevel 보다 작은 문제
                    recommend3ProblemNumber(level = problemLevel, problemNumberType = ProblemNumberType.SMALL)
                }
                bw.write("$recommandProblemNumber\n")
            }
        }
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun updateProblemsTree(problemNumber: Int, problemLevel: Int, problemGroup: Int) {
    // <분류 - <난이도 - <문제 번호, 아무값>>>
    groupedProblemsTree[problemGroup] =
        groupedProblemsTree.getOrDefault(problemGroup, TreeMap<Int, TreeMap<Int, Int>>()).apply {
            // this = <난이도 - <문제 번호, 아무값>>
            this[problemLevel] = this.getOrDefault(problemLevel, TreeMap<Int, Int>()).apply {
                // this = <문제 번호, 아무값>>
                this[problemNumber] = 0
            }
        }

    // <난이도 - <문제 번호, 아무값>>
    problemsTree[problemLevel] = problemsTree.getOrDefault(problemLevel, TreeMap<Int, Int>()).apply {
        // this = <문제 번호, 아무값>>
        this[problemNumber] = 0
    }

    val problemInfo = ProblemInfo(level = problemLevel, group = problemGroup)
    problems[problemNumber] = problemInfo
}

// groupedProblemsTree = <분류 - <난이도 - <문제 번호, 아무값>>>
// 추천 문제 리스트에서 알고리즘 분류가 group인 문제 중 가장 난이도가 type한 문제 번호를 출력한다.
// 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 큰/작은 것으로 출력한다.
private fun recommendProblemNumber(group: Int, problemLevelType: ProblemLevelType): Int {
    val problemsLevelTree = groupedProblemsTree[group]!! // <난이도 - <문제 번호, 아무값>>
    val problemLevel = if (problemLevelType == ProblemLevelType.HARD) {
        problemsLevelTree.lastKey()
    } else {
        problemsLevelTree.firstKey()
    }
    val problemsTree = problemsLevelTree[problemLevel]!!
    if (problemsTree.isEmpty()) { // 만약 해당하는 난이도에 대한 문제가 없다면 해당 난이도를 없애고 다시 조회
        groupedProblemsTree[group]!!.remove(problemLevel)
        return recommendProblemNumber(group, problemLevelType)
    }
    return if (problemLevelType == ProblemLevelType.HARD) {
        problemsTree.lastKey()
    } else {
        problemsTree.firstKey()
    }
}

// problemsTree = <난이도 - <문제 번호, 아무값>>
// 추천 문제 리스트에서 **알고리즘 분류 상관 없이** 문제 중 가장 난이도가 type한 문제 번호를 출력한다.
// 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 큰/작은 것으로 출력한다.
private fun recommend2ProblemNumber(problemLevelType: ProblemLevelType): Int {
    val problemLevel = if (problemLevelType == ProblemLevelType.HARD) {
        problemsTree.lastKey()
    } else {
        problemsTree.firstKey()
    }
    val problems = problemsTree[problemLevel]!!
    if (problems.isEmpty()) {
        problemsTree.remove(problemLevel)
        return recommend2ProblemNumber(problemLevelType)
    }
    return if (problemLevelType == ProblemLevelType.HARD) {
        problems.lastKey()
    } else {
        problems.firstKey()
    }
}

// problemsTree = <난이도 - <문제 번호, 아무값>>
// 추천 문제 리스트에서 **알고리즘 분류 상관 없이** 문제 중 가장 난이도가 level 보다 크거나 같은/작은 문제 중 가장 쉬운 문제 번호를 출력한다.
// 조건을 만족하는 문제가 여러 개라면 그 중 문제 번호가 **작은/큰** 으로 출력한다.
private fun recommend3ProblemNumber(level: Int, problemNumberType: ProblemNumberType): Int {
    val problemLevels = if (problemNumberType == ProblemNumberType.BIG) { // 난이도가 level 보다 크거나 같은
        problemsTree.tailMap(level)
    } else { // 난이도가 level 보다 작은
        problemsTree.headMap(level)
    } // problemLevels = <난이도 - <문제 번호, 아무값>>
    if (problemLevels.isEmpty()) {
        return -1
    }
    val problemLevel = if (problemNumberType == ProblemNumberType.BIG) { // 난이도가 낮은 문제 중에서
        problemLevels.firstKey()
    } else { // 난이도가 높은 문제 중에서
        problemLevels.lastKey()
    }
    val problems = problemLevels[problemLevel]!! // <문제 번호, 아무값>
    if (problems.isEmpty()) {
        problemsTree.remove(problemLevel) // 없는 난이도 제거 후 함수 함수 돌리기
        return recommend3ProblemNumber(level, problemNumberType)
    }
    return if (problemNumberType == ProblemNumberType.BIG) { // 문제 번호가 작은 것으로 출력
        problems.firstKey()
    } else { //  문제 번호가 큰 것으로 출력
        problems.lastKey()
    }
}
