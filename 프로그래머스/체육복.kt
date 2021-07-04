class Solution {
    fun solution(studentsCount: Int, _lostStudents: IntArray, _extraHaveStudents: IntArray): Int {
        val extraHaveStudents = _extraHaveStudents.filterNot { _lostStudents.contains(it) }.toMutableList()
        val lostStudents = _lostStudents.filterNot { _extraHaveStudents.contains(it) }
        var studentsThatHaveClothes = studentsCount - lostStudents.size
        lostStudents.forEach { student ->
            fun statement(it: Int) = listOf(student - 1, student, student + 1).contains(it)
            if (extraHaveStudents.any(::statement)) {
                studentsThatHaveClothes++
                extraHaveStudents.removeIf(::statement)
            }
        }
        return studentsThatHaveClothes
    }
}
