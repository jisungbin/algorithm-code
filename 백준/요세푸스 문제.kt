fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    var numbers = (1..n).toMutableList()
    val deleteNumbers = mutableListOf<Int>()
    while (numbers.size != 1) {
        var index = k - 1
        if (index > numbers.lastIndex) {
            index = k % numbers.size
            if (index == 0) {
                index = numbers.lastIndex
            } else {
                index -= 1
            }
        }
        deleteNumbers.add(numbers[index])
        val newNumbers = numbers.subList(index + 1, numbers.size)
        newNumbers.addAll(numbers.subList(0, index))
        numbers = newNumbers
    }
    deleteNumbers.add(numbers.first())
    println(deleteNumbers.joinToString(prefix = "<", postfix = ">"))
}
