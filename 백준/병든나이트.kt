fun main() { // 풀이중
    val (n, m) = readLine()!!.split(" ").map { it.toInt() } // N × M 크기 체스판, 세로 × 가로
    var niteMovingCount = 0
    var niteVisitTileCount = 0

    //   나이트 이동 가능 범위
    /* - 2칸 위로, 1칸 오른쪽
       - 1칸 위로, 2칸 오른쪽
       - 1칸 아래로, 2칸 오른쪽
       - 2칸 아래로, 1칸 오른쪽
    */

    if (niteMovingCount >= 4) {
        // 이동 방법을 모두 한 번씩 사용해야 한다
    } else {
        // 이동 방법에 대한 제약이 없다
    }

    if (n * m == 1) {
        niteVisitTileCount = 1
    } else {

    }

    println(niteVisitTileCount)
}
