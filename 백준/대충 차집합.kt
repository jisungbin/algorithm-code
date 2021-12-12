import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (mapSize, map2Size) = br.readLine().split(" ").map { it.toInt() }
    val map = mutableMapOf<String, Int>()
    val map2 = mutableMapOf<String, Int>()

    val mapElements = br.readLine()!!.split(" ")
    for (i in mapElements.indices) {
        map[mapElements[i]] = 0
    }

    val map2Elements = br.readLine()!!.split(" ")
    for (i in map2Elements.indices) {
        map2[map2Elements[i]] = 0
    }

    var mapAndMap2IntersectionCount = 0
    var map2AndMapIntersectionCount = 0

    for (key in map2.keys) {
        if (map[key] != null) mapAndMap2IntersectionCount++
    }

    for (key in map.keys) {
        if (map2[key] != null) map2AndMapIntersectionCount++
    }

    bw.write("${(mapSize - mapAndMap2IntersectionCount) + (map2Size - map2AndMapIntersectionCount)}")

    br.close()
    bw.flush()
    bw.close()
}
