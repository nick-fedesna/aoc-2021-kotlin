fun main() {

    val fileName = "Day__"

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${fileName}_test")
    val p1 = part1(testInput)
    println("${fileName}_test.p1: $p1")
    check(p1 == 0)

    val p2 = part2(testInput)
    println("${fileName}_test.p2: $p2")
    check(p2 == 0)

    val input = readInput(fileName)

    println("${fileName}.p1: ${part1(input)}")
    println("${fileName}.p2: ${part2(input)}")
}
