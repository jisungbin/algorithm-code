import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val money = br.readLine()!!.toInt()
    val stocks = br.readLine()!!.split(" ").map(String::toInt)

    var bnpStockCount = 0
    var bnpMoney = money
    var bnpResult = 0

    stocks.forEach { stock ->
        if (stock <= bnpMoney) {
            val buyableStockCount = bnpMoney / stock
            bnpStockCount += buyableStockCount
            bnpMoney -= buyableStockCount * stock
        }
    }
    bnpResult = stocks[13] * bnpStockCount + bnpMoney

    var timingStockCount = 0
    var timingMoney = money
    var timingResult = 0

    var downCount = 0
    var upCount = 0

    for (i in 1 until 14) {
        val beforeStock = stocks[i - 1]
        val nowStock = stocks[i]

        if (beforeStock > nowStock) {
            downCount++
            upCount = 0
            if (downCount == 3) {
                val buyableStockCount = timingMoney / nowStock
                timingStockCount += buyableStockCount
                timingMoney -= buyableStockCount * nowStock
            }
        }

        if (beforeStock < nowStock) {
            upCount++
            downCount = 0
            if (upCount == 3) {
                timingMoney += timingStockCount * nowStock
                timingStockCount = 0
            }
        }

        if (beforeStock == nowStock) {
            upCount = 0
            downCount = 0
        }
    }
    timingResult = stocks[13] * timingStockCount + timingMoney

    bw.write(
        if (bnpResult > timingResult) {
            "BNP"
        } else if (bnpResult < timingResult) {
            "TIMING"
        } else {
            "SAMESAME"
        }
    )

    br.close()
    bw.flush()
    bw.close()
}
