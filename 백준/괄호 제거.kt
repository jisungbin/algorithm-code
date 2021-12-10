import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

private const val OpenBracket = "("
private const val CloseBracket = ")"

private data class BracketIndex(val open: Int, val close: Int)

private lateinit var originalExpression: String
private lateinit var expression: MutableList<String>
private val bracketsIndices = mutableListOf<BracketIndex>()
private val bracketRemovedExpressionsSet = mutableSetOf<String>()

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    originalExpression = br.readLine().trim()
    expression = originalExpression.split("").toMutableList()
    val openBracketsIndex = Stack<Int>()

    for (index in expression.indices) {
        val char = expression[index]
        if (char == OpenBracket) openBracketsIndex.push(index)
        else if (char == CloseBracket) {
            bracketsIndices.add(BracketIndex(open = openBracketsIndex.pop(), close = index))
        }
    }

    removeBracket()

    val bracketRemovedExpressionsList = bracketRemovedExpressionsSet.map { it.replace(" ", "") }.distinct().sorted()
    for (i in bracketRemovedExpressionsList.indices) {
        bw.write("${bracketRemovedExpressionsList[i]}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

/*
1+(2*(3+4))
1+(2*3+4)
1+2*(3+4)
1+2*3+4

이런 경우처럼 중간에 괄호가 안 지워졌을수도 있기 때문에
괄호 지운것과 안 지운것 2가지 케이스로 진행
*/
private fun removeBracket(step: Int = 0) {
    if (step != bracketsIndices.size) {
        val bracketIndices = bracketsIndices[step]
        expression[bracketIndices.open] = " "
        expression[bracketIndices.close] = " "
        var expressionString = expression.joinToString("")
        if (originalExpression != expressionString) {
            bracketRemovedExpressionsSet.add(expressionString)
        }
        removeBracket(step + 1)

        expression[bracketIndices.open] = OpenBracket
        expression[bracketIndices.close] = CloseBracket
        expressionString = expression.joinToString("")
        if (originalExpression != expressionString) {
            bracketRemovedExpressionsSet.add(expressionString)
        }
        removeBracket(step + 1)
    }
}
