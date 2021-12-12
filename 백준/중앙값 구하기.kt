import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Collections
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val caseCount = br.readLine()!!.toInt()
    repeat(caseCount) {
        val numbersPriorityMaxQueue = PriorityQueue<Int>(Collections.reverseOrder())
        val numbersPriorityMinQueue = PriorityQueue<Int>()
        val numbersCount = br.readLine()!!.toInt()
        val numbers = mutableListOf<String>()
        if (numbersCount <= 10) {
            numbers.addAll(br.readLine().split(" "))
        } else {
            repeat(numbersCount / 10 + 1) {
                numbers.addAll(br.readLine().split(" "))
            }
        }
        val centerNumbers = StringBuilder()
        var numberAddedCount = 0
        for (i in 0 until numbersCount) {
            val number = numbers[i]
            // 숫자를 차례대로 왼쪽, 오른쪽에 넣음
            if (numbersPriorityMaxQueue.size == numbersPriorityMinQueue.size) {
                numbersPriorityMaxQueue.offer(number.toInt())
            } else {
                numbersPriorityMinQueue.offer(number.toInt())
            }
            // numbersPriorityMaxQueue은 중앙값 이하의 숫자만 받을 수 있음
            if (!numbersPriorityMinQueue.isEmpty()) {
                if (numbersPriorityMaxQueue.peek() > numbersPriorityMinQueue.peek()) {
                    numbersPriorityMaxQueue.offer(numbersPriorityMinQueue.poll())
                    numbersPriorityMinQueue.offer(numbersPriorityMaxQueue.poll())
                }
            }
            if (i % 2 == 0) {
                // 한 줄의 최대 10개씩 출력
                if (numberAddedCount == 9 || i == numbersCount - 1) {
                    centerNumbers.append("${numbersPriorityMaxQueue.peek()}\n")
                    numberAddedCount = 0
                } else {
                    centerNumbers.append("${numbersPriorityMaxQueue.peek()} ")
                }
                numberAddedCount++
            }
        }
        bw.write("${(numbersCount + 1) / 2}\n")
        bw.write("$centerNumbers")
    }

    br.close()
    bw.flush()
    bw.close()
}
