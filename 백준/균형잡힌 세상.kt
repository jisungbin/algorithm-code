import Bracket.getOpenBracket
import Bracket.isBracket
import Bracket.isCloseBracket
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private object Bracket {
    const val OpenSmall = "("
    const val CloseSmall = ")"
    const val OpenBig = "["
    const val CloseBig = "]"
    val All = listOf(OpenSmall, CloseSmall, OpenBig, CloseBig)

    fun String.isBracket() = All.contains(this)
    fun String.isCloseBracket() = this == CloseSmall || this == CloseBig
    fun String.getOpenBracket() = if (this == CloseSmall) OpenSmall else OpenBig
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val text = br.readLine()!!
        if (text != ".") {
            bw.write("${check(text)}\n")
        } else {
            break
        }
    }

    br.close()
    bw.flush()
    bw.close()
}

private fun check(text: String): String {
    val brackets = ArrayDeque<String>()
    text.split("").forEach { char ->
        if (char.isBracket()) {
            brackets.addLast(char)
        }
        if (char.isCloseBracket()) {
            brackets.removeLast()
            val lastBracket = brackets.lastOrNull() ?: return "no"
            val needOpenBracket = char.getOpenBracket()
            if (lastBracket == needOpenBracket) {
                brackets.removeLast()
            } else {
                return "no"
            }
        }
    }
    return if (brackets.isEmpty()) "yes" else "no"
}
