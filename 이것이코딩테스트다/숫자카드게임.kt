fun main() {
    val n = readLine()!!.split(" ").first().toInt()
    val result = mutableListOf<Int>() // 이거 더 짧게 될거같은데
    repeat(n) { result.add(readLine()!!.split(" ").minOf { it.toInt() }) }
    println(result.toList().maxOrNull()!!)
}
