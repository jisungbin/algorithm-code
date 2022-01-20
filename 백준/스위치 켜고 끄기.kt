import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val switchCount = br.readLine()!!.toInt()
    val input = br.readLine()!!.split(" ").map(String::toInt).toMutableList()
    val queryCount = br.readLine()!!.toInt()

    fun Int.toggle() = if (this == 1) 0 else 1

    fun switch(gender: Int, number: Int) {
        when (gender) {
            1 -> { // 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다.
                // 즉, 스위치가 켜져 있으면 끄고, 꺼져 있으면 켠다.
                var time = 0
                while (true) {
                    val newNumber = number * ++time
                    if (newNumber > switchCount) return
                    input[newNumber - 1] = input[newNumber - 1].toggle()
                }
            }
            2 -> { // 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서
                // 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를 모두 바꾼다.

                // 양쪽이 같으면 다음 체크 하고
                // 다음에서 다르거나 범위 초과면 이전으로 진행
                val newNumber = number - 1
                var (lastStartNumber, lastEndNumber) = listOf(newNumber, newNumber)
                while (true) {
                    val (startNumber, endNumber) = listOf(lastStartNumber - 1, lastEndNumber + 1)
                    if (
                        startNumber in 0 until switchCount &&
                        endNumber in 0 until switchCount &&
                        input[startNumber] == input[endNumber]
                    ) {
                        lastStartNumber = startNumber
                        lastEndNumber = endNumber
                        continue
                    }
                    for (i in lastStartNumber..lastEndNumber) {
                        input[i] = input[i].toggle()
                    }
                    return
                }
            }
        }
    }

    repeat(queryCount) {
        val (gender, number) = br.readLine()!!.split(" ").map(String::toInt)
        switch(gender, number)
    }

    var printCount = 0
    for (char in input) {
        printCount++
        bw.write("$char ")
        if (printCount == 20) {
            bw.write("\n")
            printCount = 0
        }
    }

    br.close()
    bw.flush()
    bw.close()
}
