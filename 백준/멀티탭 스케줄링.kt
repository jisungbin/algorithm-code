fun main() {
    val (tabs, _) = readLine()!!.split(" ").map { it.toInt() }
    var _goods = readLine()!!.split(" ").map { it.toInt() }.toMutableList()
    val usedPlug = _goods.take(tabs).distinct().toMutableList()
    var plugChangeCount = 0
    _goods = _goods.drop(tabs).toMutableList()
    _goods.forEachIndexed { index, good ->
        val goods = _goods.drop(index)
        if (!usedPlug.contains(good)) {
            if (usedPlug.size != tabs) {
                usedPlug.add(good)
            } else {
                if (usedPlug.any { plug -> !goods.contains(plug) }) {
                    val unusedPlug = usedPlug.last { plug -> !goods.contains(plug) }
                    usedPlug.remove(unusedPlug)
                    usedPlug.add(good)
                    plugChangeCount++
                } else {
                    val maxPlugIndex = usedPlug.maxOf { plug -> goods.indexOf(plug) }
                    val maxIndexPlug = usedPlug.first { plug -> goods.indexOf(plug) == maxPlugIndex }
                    usedPlug.remove(maxIndexPlug)
                    usedPlug.add(good)
                    plugChangeCount++
                }
            }
        }
    }
    println(plugChangeCount)
}
