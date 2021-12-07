import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val towersCount = br.readLine()!!.toInt()
    val towers = ArrayDeque<Pair<Int, Int>>()
    val laseredTowerIndex = ArrayDeque<Int>()

    loop@ for (tower in (1..towersCount).zip(br.readLine()!!.split(" ").map { it.toInt() })) {
        var frontTower = towers.lastOrNull()
        if (frontTower == null) {
            towers.addLast(tower)
            laseredTowerIndex.addLast(0)
            continue
        }
        while (towers.isNotEmpty()) {
            frontTower = towers.removeLast()
            if (frontTower.second > tower.second) {
                towers.addLast(frontTower)
                towers.addLast(tower)
                laseredTowerIndex.addLast(frontTower.first)
                continue@loop
            }
        }
        towers.addLast(tower)
        laseredTowerIndex.addLast(0)
    }

    bw.write(laseredTowerIndex.joinToString(" "))
    br.close()
    bw.flush()
    bw.close()
}
