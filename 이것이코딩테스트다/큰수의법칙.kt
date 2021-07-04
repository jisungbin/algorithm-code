fun main() {
    val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
    val (first, second) = readLine()!!.split(" ")
        .map { it.toInt() }
        .sortedDescending()

    var result = 0
    var index = 0

    repeat(m) {
        if (index < k) {
            result += first
            index++
        } else {
            result += second
            index = 0
        }
    }

    println(result)
}
