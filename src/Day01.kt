fun main() {
    var lastDepth: Int

    fun part1(input: List<String>): Int {
        lastDepth = -1
        var increasedCount = 0
        input.forEach {
            val depth = it.toInt()
            if (lastDepth != -1) {
                if (depth > lastDepth)
                    increasedCount++
            }
            lastDepth = depth
        }
        return increasedCount
    }

    fun part2(input: List<String>): Int {
        lastDepth = -1
        var increasedCount = 0
        input.map { it.toInt() }
            .rollingWindow(3)
            .map { it.sum() }
            .forEach {
                if (lastDepth != -1) {
                    if (it > lastDepth) {
                        increasedCount++
                    }
                }
                lastDepth = it
            }
        return increasedCount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}