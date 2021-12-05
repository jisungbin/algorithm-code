import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (month, day) = br.readLine()!!.split(" ").map { it.toInt() }

    val date = LocalDate.of(2007, month, day)
    bw.write("${date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).uppercase()}\n")

    br.close()
    bw.flush()
    bw.close()
}
