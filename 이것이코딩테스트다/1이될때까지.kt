  
fun main() {
    var (n, k) = readLine()!!.split(" ").map { it.toInt() }
    var result = 0
    while (n > 1) {
        if (n % k == 0) {
            n /= k
            result += 1
        } else {
            n += 1
            result += 1
        }
    }

    println(result)
}
