import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val buildings = List(br.readLine()!!.toInt()) { br.readLine()!!.toInt() }
    val buildingsStack = Stack<Int>()
    var building: Int
    var canSeeBuildingCount = 0L

    for (i in buildings.indices) {
        building = buildings[i]
        if (buildingsStack.isEmpty()) {
            buildingsStack.push(building)
        } else {
            // 만약 내가 볼 수 있는 빌딩 높이가 있다면
            // 내가 볼 수 있는 빌딩 높이가 비어있지 않고, 현재 내 빌딩 높이가 내 빌딩에서 보이는 빌딩 높이 스택의 맨 위 값 보다 크거나 같다면
            // 내 빌딩에서 보이는 빌딩 높이 스택의 맨 위 값을 지움
            // (그런데 자신이 위치한 빌딩보다 높거나 같은 빌딩이 있으면 그 다음에 있는 모든 빌딩의 옥상은 보지 못한다. 조건을 위해)

            // 현재 내 빌딩보다 더 높은 빌딩이 보이면 어차피 내 뒤에 있는 빌딩들은 내 다음 빌딩들을 보지 못함
            // 위 원리를 이용하여 보이는 빌딩들의 개수를 구할 수 있음
            while (buildingsStack.isNotEmpty() && buildingsStack.peek() <= building) {
                buildingsStack.pop()
            }
            // 위 과정을 거치고 내 빌딩에서 보이는 빌딩 높이 스택의 사이즈 만큼을 볼 수 있는 빌딩 개수에 넣어줌
            canSeeBuildingCount += buildingsStack.size
            buildingsStack.push(building)
        }
    }

    bw.write("$canSeeBuildingCount")
    br.close()
    bw.flush()
    bw.close()
}
