import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var word: List<String>
    var wordStack = Stack<String>()
    var goodWordCount = 0
    val words = List(br.readLine()!!.toInt()) { br.readLine()!! }
    for (i in words.indices) {
        word = words[i].split("").drop(1).dropLast(1)
        for (j in word.indices) {
            if (wordStack.isEmpty()) {
                wordStack.push(word[j])
            } else {
                // 만약 기존에 들어간 문자랑 현재 문자랑 같으면 둘이 매치되므로 스택에서 삭제
                // 어차피 스택에서 삭제되므로 현재 문자는 스택에 넣지 않음
                if (wordStack.peek() == word[j]) {
                    wordStack.pop()
                } else {
                    // 만약 같지 않다면 다음에 올 문자랑 같을 경우를 위해 스택에 넣어줌
                    // 문제 이해는 https://scarlettb.tistory.com/18 사진 참고
                    wordStack.push(word[j])
                }
            }
        }
        if (wordStack.isEmpty()) {
            goodWordCount++
        }
        wordStack = Stack<String>()
    }

    bw.write("$goodWordCount")
    br.close()
    bw.flush()
    bw.close()
}
