data class Dot(val x: Int, val y: Int)

private var Q1 = 0
private var Q2 = 0
private var Q3 = 0
private var Q4 = 0
private var AXIS = 0

fun main() {
    var size = readLine()!!.toInt()
    var dots = List<Dot>(size){ 
        val dot = readLine()!!.toString().split(" ")
        Dot(dot[0].toInt(), dot[1].toInt())
    }
    dots.forEach {
        it.run {
            if (x == 0 || y == 0) AXIS++
            else if (x > 0) {
                if (y > 0) Q1++
                else Q4++
            }
            else { // x 미만
                if (y > 0) Q2++
                else Q3++
            }
        }
    }
    println("""
        Q1: $Q1
        Q2: $Q2
        Q3: $Q3
        Q4: $Q4
        AXIS: $AXIS
    """.trimIndent())
}
