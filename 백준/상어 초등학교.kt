import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

private data class Student(val column: Int, val row: Int, val friends: List<Int>)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine()!!.toInt()
    val NN = N * N
    val columnUpper = listOf(1, -1, 0, 0)
    val rowUpper = listOf(0, 0, 1, -1)

    val classroom = List(N) { MutableList(N) { 0 } }
    val nearEmptySeatCount = List(N) { MutableList(N) { 0 } }
    val students = mutableMapOf<Int, Student>()

    fun calcNearEmptySeatCount() {
        for (column in 0 until N) {
            for (row in 0 until N) {
                var count = 4
                if (column == 0 || column == N - 1) count--
                if (row == 0 || row == N - 1) count--
                nearEmptySeatCount[column][row] = count
            }
        }
    }

    fun findSeat(num: Int, friends: List<Int>) {
        val nearCount = List(N) { MutableList(N) { 0 } }

        for (friend in friends) {
            val student = students[friend] ?: continue
            val (column, row, _) = student
            repeat(4) { upperIndex ->
                val uppedColumn = column + columnUpper[upperIndex]
                val uppedRow = row + rowUpper[upperIndex]
                if (uppedColumn in 0 until N && uppedRow in 0 until N && classroom[uppedColumn][uppedRow] == 0) {
                    nearCount[uppedColumn][uppedRow]++
                }
            }
        }

        var emptyCountMax = -1
        var nearCountMax = -1
        var choiceColumn = -1
        var choiceRow = -1

        for (column in 0 until N) {
            for (row in 0 until N) {
                if (classroom[column][row] != 0) continue
                if (nearCountMax < nearCount[column][row]) {
                    choiceColumn = column
                    choiceRow = row
                    nearCountMax = nearCount[column][row]
                    emptyCountMax = nearEmptySeatCount[column][row]
                } else if (nearCountMax == nearCount[column][row] && emptyCountMax < nearEmptySeatCount[column][row]) {
                    emptyCountMax = nearEmptySeatCount[column][row]
                    choiceColumn = column
                    choiceRow = row
                }
            }
        }

        classroom[choiceColumn][choiceRow] = num
        students[num] = Student(column = choiceColumn, row = choiceRow, friends = friends)

        repeat(4) { upperIndex ->
            val uppedColumn = choiceColumn + columnUpper[upperIndex]
            val uppedRow = choiceRow + rowUpper[upperIndex]
            if (uppedColumn in 0 until N && uppedRow in 0 until N && classroom[uppedColumn][uppedRow] == 0) {
                nearEmptySeatCount[uppedColumn][uppedRow]--
            }
        }
    }

    calcNearEmptySeatCount()

    for (column in 0 until NN) {
        val (num, s1, s2, s3, s4) = br.readLine()!!.split(" ").map(String::toInt)
        findSeat(num = num, friends = listOf(s1, s2, s3, s4))
    }

    var answer = 0
    for (i in 1 .. NN) {
        val student = students[i]!!
        var count = 0
        for (friendNum in student.friends) {
            val friend = students[friendNum]!!
            if (abs(friend.column - student.column) + abs(friend.row - student.row) == 1) {
                count++
            }
        }

        when (count) {
            1 -> answer++
            2 -> answer += 10
            3 -> answer += 100
            4 -> answer += 1000
        }
    }
    bw.write("$answer")

    bw.flush()
    bw.close()
    br.close()
}
