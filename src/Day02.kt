fun main() {

    fun List<String>.toDirections(): List<Pair<String, Int>> {
        return this.map {
            val parts = it.split(" ")
            parts[0] to parts[1].toInt()
        }
    }

    fun part1(input: List<String>): Int {
        var depth = 0
        var pos = 0
        input.toDirections()
            .forEach {
                when (it.first) {
                    "forward" -> pos += it.second
                    "up" -> depth -= it.second
                    "down" -> depth += it.second
                }
            }
        return depth * pos
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var aim = 0
        var pos = 0
        input.toDirections()
            .forEach {
                when(it.first) {
                    "forward" -> {
                        pos += it.second
                        depth += (aim * it.second)
                    }
                    "up" -> aim -= it.second
                    "down" -> aim += it.second
                }
            }
        return depth * pos
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    val p2 = part2(testInput)
    println("part2_test: $p2")
    check(p2 == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}



