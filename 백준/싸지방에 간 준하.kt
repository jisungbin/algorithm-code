import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

private data class Computer(val startTime: Int, val endTime: Int)
private data class UsedComputer(val index: Int, val endTime: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val computersCount = br.readLine()!!.toInt()
    val computersIndicesForEachUseCount = mutableMapOf<Int, Int>() //  <자리 - 휫수>

    val computers = PriorityQueue<Computer>(compareBy { it.startTime }) // 시작 시간으로 오름차순 정렬
    val usedComputers = PriorityQueue<UsedComputer>(compareBy { it.endTime }) // 끝난 시간으로 오름차순 정렬
    val usableComputersIndices = PriorityQueue<Int>() // 사용 가능한 컴퓨터 번호

    repeat(computersCount) {
        val (startTime, endTime) = br.readLine()!!.split(" ").map { it.toInt() }
        computers.add(Computer(startTime = startTime, endTime = endTime))
    }

    var index = 0 // 컴퓨터 번호

    while (computers.isNotEmpty()) {
        val computer = computers.poll()
        if (usedComputers.isEmpty()) { // 사용중인 컴퓨터가 없다면
            usedComputers.add(UsedComputer(index = ++index, endTime = computer.endTime))
            computersIndicesForEachUseCount[index] = 1
        } else {
            // 사용할 수 있는 컴퓨터 구하기
            while (usedComputers.isNotEmpty()) {
                val usedComputer = usedComputers.peek()
                if (usedComputer.endTime < computer.startTime) {
                    usableComputersIndices.add(usedComputer.index) // 사용할 수 있는 컴퓨터중에 가장 빠른 번호를 구하기 위함
                    usedComputers.poll()
                } else {
                    break
                }
            }

            if (usableComputersIndices.isEmpty()) { // 사용할 수 있는 컴퓨터가 없다면
                usedComputers.add(UsedComputer(index = ++index, endTime = computer.endTime))
                computersIndicesForEachUseCount[index] = 1
            } else {
                val usableComputerIndex = usableComputersIndices.poll()
                computersIndicesForEachUseCount[usableComputerIndex] =
                    computersIndicesForEachUseCount[usableComputerIndex]!! + 1
                usedComputers.add(UsedComputer(index = usableComputerIndex, endTime = computer.endTime))
            }
        }
    }

    bw.write("${computersIndicesForEachUseCount.size}\n")
    bw.write(computersIndicesForEachUseCount.values.joinToString(" "))
    br.close()
    bw.flush()
    bw.close()
}
