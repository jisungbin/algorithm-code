import kotlin.math.min

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    println(
        if (n == 1) {
            1
        } else if (n == 2) {
            min(4, ((m - 1) / 2) + 1)
        } else {
            if (m < 7) min(4, m)
            else 3 + (m - 5)
        }
    )
}
