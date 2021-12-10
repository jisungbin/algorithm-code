import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack

private object Bracket {
    const val SmallOpen = "("
    const val SmallClose = ")"

    const val BigOpen = "["
    const val BigClose = "]"
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val brackets = br.readLine().split("").drop(1).dropLast(1)
    val bracketsStack = Stack<Pair<Int, String>>()
    val numberStack = Stack<Pair<Int, Int>>()

    for (index in brackets.indices) {
        val bracket = brackets[index]
        if (bracketsStack.isEmpty()) {
            bracketsStack.push(index to bracket)
        } else {
            val frontBracket = bracketsStack.peek()
            // () 상태 -> 1, 3 조건 계산
            if (bracket == Bracket.SmallClose && frontBracket.second == Bracket.SmallOpen) {
                // numberStack 초기 값 넣기
                // 1. ‘()’ 인 괄호열의 값은 2이다.
                if (numberStack.isEmpty()) {
                    numberStack.push(index to 2)
                } else { // 3. ‘(X)’ 의 괄호값은 2×값(X) 으로 계산된다. 이 부분 계산
                    var frontNumberStack = numberStack.peek()
                    // ( frontNumberStack[0]...frontNumberStack[n] ) 상태
                    // frontNumberStack[0]...frontNumberStack[n] 을 다 더해주는 과정이 필요함
                    var frontNumberStackSum = 0
                    while (frontNumberStack.first in frontBracket.first..index) {
                        numberStack.pop()
                        frontNumberStackSum += frontNumberStack.second
                        if (numberStack.isNotEmpty()) {
                            frontNumberStack = numberStack.peek()
                        } else {
                            break
                        }
                    }
                    if (frontNumberStackSum == 0) { // () () 상태 -> 1번 조건 따름
                        numberStack.push(index to 2)
                    } else {
                        numberStack.push(index to frontNumberStackSum * 2)
                    }
                }
                bracketsStack.pop()
            } else if (bracket == Bracket.BigClose && frontBracket.second == Bracket.BigOpen) { // [] 상태 -> 2, 4 조건 계산
                // numberStack 초기 값 넣기
                // 2. ‘[]’ 인 괄호열의 값은 3이다.
                if (numberStack.isEmpty()) {
                    numberStack.push(index to 3)
                } else { // 4. ‘[X]’ 의 괄호값은 3×값(X) 으로 계산된다. 이 부분 계산
                    var frontNumberStack = numberStack.peek()
                    // [ frontNumberStack[0]...frontNumberStack[n] ] 상태
                    // frontNumberStack[0]...frontNumberStack[n] 을 다 더해주는 과정이 필요함
                    var frontNumberStackSum = 0
                    while (frontNumberStack.first in frontBracket.first..index) {
                        numberStack.pop()
                        frontNumberStackSum += frontNumberStack.second
                        if (numberStack.isNotEmpty()) {
                            frontNumberStack = numberStack.peek()
                        } else {
                            break
                        }
                    }
                    if (frontNumberStackSum == 0) { // [] [] 상태 -> 2번 조건 따름
                        numberStack.push(index to 3)
                    } else {
                        numberStack.push(index to frontNumberStackSum * 3)
                    }
                }
                bracketsStack.pop()
            } else {
                bracketsStack.push(index to bracket)
            }
        }
    }

    if (bracketsStack.isNotEmpty()) {
        bw.write("0")
    } else {
        bw.write("${numberStack.sumOf { it.second }}")
    }

    br.close()
    bw.flush()
    bw.close()
}
