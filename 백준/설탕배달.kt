fun main() {
    val total = readLine()!!.toInt()
    val three = total / 3
    var five = total / 5

    println(
        if (five != 0) {
            var remainder = total % 5
            if (remainder == 0) five
            else {
                if (remainder % 3 == 0) five + 1
                else {
                    var result = -1
                    while (five > 0) {
                        val sum = 5 * --five
                        remainder = total - sum
                        if (remainder % 3 == 0) {
                            result = sum / 5 + remainder / 3
                            break
                        }
                    }
                    result
                }
            }
        } else {
            if (total % 3 == 0) three else -1
        }
    )
}
