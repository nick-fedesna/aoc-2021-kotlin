import kotlin.experimental.and
import kotlin.experimental.inv

fun main() {

    fun part1(input: List<String>): Int {
        val data = ArrayList<Pair<Int, Int>>(input.size)
        for (n in 1..input[0].length) {
            data.add(0 to 0)
        }
        input.forEach {
            it.toCharArray().forEachIndexed { index, char ->
                val count = data[index]// ?: 0 to 0
                val digit = char.digitToInt(2)
                if (digit == 0) {
                    data[index] = count.first + 1 to count.second
                } else {
                    data[index] = count.first to count.second + 1
                }
            }
        }
        val gammaString = buildString {
            data.forEach {
                if (it.first > it.second) {
                    append(0)
                } else {
                    append(1)
                }
            }
        }
        println("gamma: $gammaString")
        val gamma = gammaString.toShort(2)
        val max = "1".repeat(gammaString.length).toShort(2)
        val epsilon = gamma.inv().and(max)
        return (gamma * epsilon)
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val p1 = part1(testInput)
    println("part1_test: $p1")
    check(p1 == 198)

    val p2 = part2(testInput)
    println("part2_test: $p2")
    check(p2 == 0)

    val input = readInput("Day03")

    input.assertAllStringsSameLength()

    println(part1(input))
    println(part2(input))
}



