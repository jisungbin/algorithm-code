import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

private const val OpenBracket = "("
private const val CloseBracket = ")"

private fun String.isOperator() = listOf("-", "+", "*", "/").contains(this)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val expression = br.readLine()!!.trim().split("")
    val operators = Stack<String>()
    val newExpresion = StringBuilder()

    for (i in expression.indices) {
        with(expression[i]) {
            when {
                isOperator() -> {
                    // 연산자 만날 때 마다 괄호 계산 실행
                    // A+B*C-D/E -> ABC*+DE/- 가 되야함 (과정 대입해보면서 생각해보기)
                    while (operators.isNotEmpty() && priority(operators.peek()) >= priority(this)) {
                        newExpresion.append(operators.pop())
                    }
                    operators.push(this)
                }
                equals(OpenBracket) -> {
                    operators.push(OpenBracket)
                }
                equals(CloseBracket) -> {
                    // ~~~) 인 상황 -> 괄호 안 연산자는 우선 배정 해줘야 하므로 이 역할을 담당함
                    // ( 만나기 전까지 들어있는 연산자 배정
                    while (operators.isNotEmpty() && operators.peek() != OpenBracket) {
                        newExpresion.append(operators.pop())
                    }
                    operators.pop() // ( 제거
                }
                else -> {
                    newExpresion.append(this)
                }
            }
        }
    }

    repeat(operators.size) {
        newExpresion.append(operators.pop())
    }

    bw.write(newExpresion.toString())
    br.close()
    bw.flush()
    bw.close()
}

private fun priority(operator: String) = when (operator) {
    "*", "/" -> 2
    "-", "+" -> 1
    else -> 0
}
