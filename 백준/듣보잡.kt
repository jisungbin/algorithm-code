import java.io.BufferedReader
import java.io.InputStreamReader

// a.intersect(b) 로 중복되는 요소 가져올 수 있음
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val peopleCount = br.readLine()!!.split(" ").sumOf { it.toInt() }
    val names = mutableListOf<String>()

    repeat(peopleCount) {
        names.add(br.readLine()!!)
    }

    val noPlacedPeoples = names.groupBy { it }.filter { it.value.size > 1 }.values.flatten().distinct().sorted()

    println(noPlacedPeoples.size)
    noPlacedPeoples.forEach(::println)

    br.close()
}
