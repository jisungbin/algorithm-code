import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val minHeap = PriorityQueue<Int>() // 오름차순 -> 작은 숫자가 먼저 나옴
    val maxHeap = PriorityQueue<Int>(Collections.reverseOrder()) // 내림차순 -> 큰 숫자가 먼저 나옴

    val count = br.readLine()!!.toInt()
    repeat(count) { index ->
        val isEven = (index + 1) % 2 == 0
        val number = br.readLine()!!.toInt()
        if (isEven) { // 짝수 번째
            minHeap.offer(number)
        } else { // 홀수 번째
            maxHeap.offer(number)
        }
        // minHeap: 맨 앞에 제일 작은 숫자
        // maxHeap: 맨 앞에 제일 큰 숫자
        // 문제는 제일 작은 숫자를 출력해야 함
        // 따라서 maxHeap에 제일 작은 숫자가 맨 앞에 들어가게 만들어줌
        if (minHeap.isNotEmpty() && minHeap.peek() < maxHeap.peek()) { // 최소힙의 부모노드가 최대힙의 부모노드보다 값이 커야함
            val min = minHeap.poll()
            minHeap.offer(maxHeap.poll())
            maxHeap.offer(min)
        }
        bw.write("${maxHeap.peek()}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
