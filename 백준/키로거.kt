import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val passwordKeylogs = List(br.readLine()!!.toInt()) { br.readLine()!! }
    var passwordKeylog: List<String>
    var passwordKey: String
    var password = Stack<String>()
    val passwordBackStack = Stack<String>()

    for (i in passwordKeylogs.indices) {
        passwordKeylog = passwordKeylogs[i].split("").drop(1).dropLast(1)
        for (j in passwordKeylog.indices) {
            passwordKey = passwordKeylog[j]
            when (passwordKey) {
                "<" -> {
                    if (password.isNotEmpty()) {
                        passwordBackStack.push(password.pop())
                    }
                }
                ">" -> {
                    if (passwordBackStack.isNotEmpty()) {
                        password.push(passwordBackStack.pop())
                    }
                }
                "-" -> {
                    if (password.isNotEmpty()) {
                        password.pop()
                    }
                }
                else -> {
                    password.push(passwordKey)
                }
            }
        }
        repeat(passwordBackStack.size) {
            password.push(passwordBackStack.pop())
        }
        bw.write("${password.joinToString("")}\n")
        password = Stack<String>()
    }

    br.close()
    bw.flush()
    bw.close()
}
