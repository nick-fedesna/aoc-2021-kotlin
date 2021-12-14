fun main() {

    val fileName = "Day06"

    fun List<Int>.afterDays(days: Int): Long {
        var census = mutableMapOf<Int, Long>()
        forEach {
            census[it] = (census[it] ?: 0L) + 1L
        }
        census = census.toSortedMap()
        var day = 0
        while (day < days) {
            val spawning = census[0] ?: 0
            for (i in 0..7) {
                census[i] = census[i + 1]
            }
            census[6] = (census[6] ?: 0) + spawning
            census[8] = spawning
            day++
        }
        return census.values.sum()
//
//        for (n in 1..days) {
//            var babies = 0
//            population = population.map {
//                if (it > 0) {
//                    it - 1
//                } else {
//                    babies++
//                    6
//                }
//            }
//            population = population + Array(babies) { 8 }
//        }
//        return population.size
    }

    fun part1(input: List<String>): Long {
        val initialPopulation = input.first().split(",").map { it.toInt() }
        return initialPopulation.afterDays(80)
    }

    fun part2(input: List<String>): Long {
        val initialPopulation = input.first().split(",").map { it.toInt() }
        return initialPopulation.afterDays(256).toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${fileName}_test")
    val p1 = part1(testInput)
    println("${fileName}_test.p1: $p1")
    check(p1 == 5934L)

    val p2 = part2(testInput)
    println("${fileName}_test.p2: $p2")
    check(p2 == 26984457539L)

    val input = readInput(fileName)

    println("${fileName}.p1: ${part1(input)}")
    println("${fileName}.p2: ${part2(input)}")
}
