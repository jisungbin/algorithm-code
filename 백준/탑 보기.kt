import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack
import kotlin.math.abs
import kotlin.math.min

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val towerSize = br.readLine()!!.toInt()
    val towers = (1..towerSize).zip(br.readLine()!!.split(" ").map { it.toInt() })
    var canSeeTowersStack = Stack<Pair<Int, Int>>()
    val canSeeTowersIndexForEachTowers = MutableList(towerSize) { 0 } // 각 건물마다 볼 수 있는 건물들의 번호
    val canSeeTowersCountForEachTowers = MutableList(towerSize) { 0 } // 각 건물마다 볼 수 있는 건물들의 개수

    for (i in towers.indices) { // 오른쪽에서 볼 수 있는 건물 계산
        val tower = towers[i]
        while (canSeeTowersStack.isNotEmpty() && canSeeTowersStack.peek().second <= tower.second) {
            canSeeTowersStack.pop()
        }
        canSeeTowersCountForEachTowers[i] = canSeeTowersStack.size
        if (canSeeTowersStack.isNotEmpty()) {
            canSeeTowersIndexForEachTowers[i] = canSeeTowersStack.peek().first
        }
        canSeeTowersStack.push(tower)
    }

    canSeeTowersStack = Stack<Pair<Int, Int>>() // clear 하면 시간 복잡도 O(n) 이므로 그냥 인스턴스 초기화
    // clear를 진행하면 내부 요소들을 다 null 로 바꿔주는 반복문을 돌린다 -> O(n)
    for (i in towers.size - 1 downTo 0) { // 왼쪽에서 볼 수 있는 건물 계산
        val tower = towers[i]
        while (canSeeTowersStack.isNotEmpty() && canSeeTowersStack.peek().second <= tower.second) {
            canSeeTowersStack.pop()
        }
        canSeeTowersCountForEachTowers[i] += canSeeTowersStack.size
        if (canSeeTowersStack.isNotEmpty()) {
            if (canSeeTowersIndexForEachTowers[i] == 0) { // 만약 비교할 거리가 없으면 바로 값을 넣어줌
                canSeeTowersIndexForEachTowers[i] = canSeeTowersStack.peek().first
            } else {
                // abs: 절대값 https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Math/abs
                val distanceFromOld = abs(canSeeTowersIndexForEachTowers[i] - tower.first)
                val distacneFromNew = abs(canSeeTowersStack.peek().first - tower.first)
                if (distanceFromOld > distacneFromNew) {
                    canSeeTowersIndexForEachTowers[i] = canSeeTowersStack.peek().first
                } else { // 만약 거리 차이가 같다면 더 작은 번호를 넣기 (그래야 더 거리가 적으니깐)
                    if (distanceFromOld == distacneFromNew) {
                        canSeeTowersIndexForEachTowers[i] =
                            min(canSeeTowersIndexForEachTowers[i], canSeeTowersStack.peek().first)
                    }
                }
            }
        }
        canSeeTowersStack.push(tower)
    }

    for (i in canSeeTowersCountForEachTowers.indices) {
        val canSeeTowerCount = canSeeTowersCountForEachTowers[i]
        val canSeeTowerIndex = canSeeTowersIndexForEachTowers[i]
        bw.write("$canSeeTowerCount")
        if (canSeeTowerIndex != 0) {
            bw.write(" $canSeeTowerIndex")
        }
        bw.write("\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
