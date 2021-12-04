import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(br.readLine()!!.toInt()) {
        val needDocumentIndex = br.readLine()!!.split(" ")[1].toInt()
        val documentsNumber = br.readLine()!!.split(" ").map { it.toInt() }
        val documents = ArrayDeque(documentsNumber.indices.zip(documentsNumber)) // 문서 이름 - 문서 우선순위
        var printedDocumentCount = 0
        if (documents.size == 1) {
            bw.write("1\n")
            return@repeat
        }
        while (true) {
            if (documents.subList(1, documents.size).any { it.second > documents.first().second }) {
                documents.addLast(documents.removeFirst())
            } else {
                val document = documents.removeFirst()
                printedDocumentCount++
                if (document.first == needDocumentIndex) break
            }
        }
        bw.write("$printedDocumentCount\n")
    }

    br.close()
    bw.flush()
    bw.close()
}
