import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

private const val MAX = 1000 + 1

private data class Emoticon(val display: Int = 1, val clipboard: Int = 0, val time: Int = 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val targetDisplay = br.readLine()!!.toInt()
    val bfsQueue: Queue<Emoticon> = LinkedList()
    val vis = List(MAX) { MutableList(MAX) { false } }
    var fastestTime = 0

    bfsQueue.offer(Emoticon())
    vis[1][0] = true

    while (bfsQueue.isNotEmpty()) {
        var (display, clipboard, time) = bfsQueue.poll()
        if (display == targetDisplay) {
            fastestTime = time
            break
        }
        time++
        if (display in 1 until MAX) {

            // 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
            if (!vis[display][display]) {
                vis[display][display] = true
                bfsQueue.offer(Emoticon(display = display, clipboard = display, time = time))
            }

            // 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
            var temp = display + clipboard
            if (clipboard > 0 && temp < MAX && !vis[temp][clipboard]) {
                vis[temp][clipboard] = true
                bfsQueue.offer(Emoticon(display = temp, clipboard = clipboard, time = time))
            }

            // 화면에 있는 이모티콘 중 하나를 삭제한다.
            temp = display - 1
            if (!vis[temp][clipboard]) {
                vis[temp][clipboard] = true
                bfsQueue.offer(Emoticon(display = temp, clipboard = clipboard, time = time))
            }
        }
    }

    bw.write("$fastestTime")

    br.close()
    bw.flush()
    bw.close()
}
