fun main() {
    val key = listOf("b", "e", "h", "p", "c", "y", "x")
    val value = listOf("v", "ye", "n", "r", "s", "u", "h")
    println(
        readLine()!!.lowercase().split("").map { 
            val index = key.indexOf(it)
            if (index != -1) {
                value[index]
            } else { 
                it
            }
        }.joinToString("")
    )
}
