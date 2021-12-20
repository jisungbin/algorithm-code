import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val caseCount = br.readLine()!!.toInt()
    repeat(caseCount) {
        val nodesSize = br.readLine()!!.toInt()
        val preOrderNodes = br.readLine()!!.split(" ") // 전위 순위는 항상 맨 앞이 루트 노드가 됨 (https://beginthread.tistory.com/79 참고)
        val inOrderNodes = br.readLine()!!.split(" ") // 전위 순위에서 구한 로트 노드로, 왼쪽 서브 트리와 오른쪽 서브 트리를 구분할 수 있음

        fun postOrder(startIndex: Int = 0, endIndex: Int = nodesSize, rootIndex: Int = 0) {
            for (i in startIndex until endIndex) {
                if (preOrderNodes[rootIndex] == inOrderNodes[i]) { // 전위 순위의 맨 앞 노드로 구한 루트 노드가 중위 순위에서 나올 때 까지 반복 (왼쪽 서브 트리/오른쪽 서브 트리 나누기)
                    // 중위 트리의 i번째 index 노드가 루트 트리일 때 재귀로 왼쪽/오른쪽 서브 트리 나뉘어서 실행
                    postOrder(startIndex = startIndex, endIndex = i, rootIndex = rootIndex + 1) // 왼쪽 서브 트리
                    postOrder( // 오른쪽 서브 트리
                        startIndex = i + 1,
                        endIndex = endIndex,
                        rootIndex = rootIndex + i - startIndex + 1 // 반복문의 시작이 startIndex 부터이므로, 구한 루트 인덱스에서 startIndex 만큼을 빼줌
                    )
                    bw.write("${preOrderNodes[rootIndex]} ") // 후위 순위는 루트 노드를 맨 마지막에 출력함
                }
            }
        }

        postOrder()
        bw.write("\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
