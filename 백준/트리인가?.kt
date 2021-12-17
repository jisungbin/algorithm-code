import java.util.Scanner

fun main() {
    val scan = Scanner(System.`in`)
    var vertex: HashMap<Int, Int> //정점의 개수를 센다
    var count = 1 //case의 순서를 센다

    while (true) {
        vertex = HashMap()
        var edge = 0 //간선의 수
        while (true) {
            val start = scan.nextInt()
            val end = scan.nextInt()
            if (start == -1 && end == -1) {
                return
            } else if (start == 0 && end == 0) {
                break
            }
            vertex[start] = vertex.getOrDefault(start, 0) // 루트 노드를 찾기 위함 (들어오는 간선이 없으면 루트)
            vertex[end] = vertex.getOrDefault(end, 0) + 1 // 들어오는 간선 개수
            edge++
        }
        var root = 0
        var isTrue = true
        val key = vertex.keys.iterator()
        while (key.hasNext()) {
            val num = key.next()
            if (vertex[num] == 0) { // 만약 들어온 간선 수가 없다면 루트 트리 (1번 조건)
                root++
            } else if (vertex[num]!! > 1) { // 들어오는 간선이 1개 이상이라면 트리가 아님 (2, 3번 조건)
                isTrue = false
                break
            }
        }
        if (vertex.size == 0) {
            println("Case $count is a tree.")
        } else if (isTrue && root == 1 && edge == vertex.size - 1) { // 노드의 개수 - 1 = 간선의 개수 (2번 조건)
            println("Case $count is a tree.")
        } else {
            println("Case $count is not a tree.")
        }
        count++
    }
}
