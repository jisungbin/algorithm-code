import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

// https://st-lab.tistory.com/164
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val string = StringBuilder()
    val count = br.readLine().toInt()

    repeat(count) {
        val clothes = mutableMapOf<String, Int>()
        repeat(br.readLine().toInt()) {
            val (_, kind) = br.readLine().split(" ")
            clothes[kind] = clothes.getOrDefault(kind, 0) + 1
        }
        var allClothesCount = 1
        for (clothesCount in clothes.values) {
            allClothesCount *= clothesCount + 1 // 안 입은 경우 추가
        }
        // 알몸인 상태를 제외 (모든 종류에서 하나도 안 입는 경우가 딱 1번 나오니까 -1)
        string.append(allClothesCount - 1).append("\n")
    }
    bw.write(string.toString())

    br.close()
    bw.flush()
    bw.close()
}
