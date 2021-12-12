import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val lastDayOfMonths = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

private val lends = mutableMapOf<String, MutableMap<String, Long>>() // <빌린 사람 이름 - <빌린 제품 이름, 빌린 분>>

private val lateReturnPeople = sortedMapOf<String, Long>() // <빌린 사람 이름 - 벌금>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val input = br.readLine().split(" ")
    val lendCount = input[0].toInt()
    val mustReturnMinute = convertMustReturnDateToMinute(input[1]) // 대출 기간
    val money = input[2].toInt()

    repeat(lendCount) {
        // lends = <빌린 사람 이름 - <빌린 제품 이름, 빌린 시간>>
        val (lendDate, lendTime, productName, lenderName) = br.readLine().split(" ")
        val lendOrReturnMinute = converLendOrReturnDateToMinute(date = lendDate, time = lendTime) // 제품을 빌리거나, 반납하는 시간
        if (lends[lenderName] == null) { // 이 사람의 신규 빌림
            lends[lenderName] = mutableMapOf(productName to lendOrReturnMinute) // lendOrReturnMinute -> 제품을 빌리는 시간
        } else { // 만약 이전에 빌린게 있다면
            val lendedMinute = lends[lenderName]!![productName] // 제품을 빌린 시간
            if (lendedMinute == null) { // 동일 제품을 이전에 빌리지 않았다면, 신규 빌림
                lends[lenderName]!![productName] = lendOrReturnMinute // lendOrReturnMinute -> 제품을 빌리는 시간
            } else { // 동일 제품을 이전에 빌렸었다면, 반납
                // 반납했어야 하는 기간 계산
                // lendOrReturnMinute -> 반납하는 시간
                // 초과된 대출 시간 = 반납하는 시간 - (빌린 시간 + 대출 기간)
                val overReturnMinute = lendOrReturnMinute - (lendedMinute + mustReturnMinute)
                if (overReturnMinute > 0) {
                    val payNeedMoney = overReturnMinute * money
                    lateReturnPeople[lenderName] = lateReturnPeople.getOrDefault(lenderName, 0) + payNeedMoney
                }
                lends[lenderName]!!.remove(productName) // 빌린 제품에서 제거
            }
        }
    }

    if (lateReturnPeople.isNotEmpty()) {
        val lateReturnPeopleNames = lateReturnPeople.keys.toList()
        for (i in lateReturnPeopleNames.indices) {
            val lateReturnPeopleName = lateReturnPeopleNames[i]
            bw.write("$lateReturnPeopleName ${lateReturnPeople[lateReturnPeopleName]}\n")
        }
    } else {
        bw.write("-1")
    }

    br.close()
    bw.flush()
    bw.close()
}

// 2021-01-01 09:12
private fun converLendOrReturnDateToMinute(date: String, time: String): Long {
    var minute = 0L
    val (_, month, day) = date.split("-").map { it.toInt() }
    val (hour, _minute) = time.split(":").map { it.toInt() }
    for (i in 0 until month) {
        minute += lastDayOfMonths[i] * 24 * 60
    }
    minute += day * 24 * 60
    minute += hour * 60 + _minute
    return minute
}

// 014/00:00
private fun convertMustReturnDateToMinute(date: String): Long {
    var minute = 0L
    val ddHhMm = date.split(":")
    minute += ddHhMm[1].toInt()
    val (day, hour) = ddHhMm[0].split("/").map { it.toInt() }
    minute += day * 24 * 60
    minute += hour * 60
    return minute
}
