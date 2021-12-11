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
                val count = data[index]
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
        println("𝝲: $gammaString")
        val gamma = gammaString.toShort(2)
        val max = "1".repeat(gammaString.length).toShort(2)
        val epsilon = gamma.inv().and(max)
        println("𝞮: ${epsilon.toString(2)}")
        return (gamma * epsilon)
    }

    fun part2(input: List<String>): Int {
        var oxygen = listOf(*input.toTypedArray())
        var carbon = listOf(*input.toTypedArray())

        val oxygenBits = ArrayList<Pair<Int, Int>>(input[0].length)
        val carbonBits = ArrayList<Pair<Int, Int>>(input[0].length)
        for (i in 0 until input[0].length) {
            var p = 0 to 0
            for (n in input.indices) {
                val d = input[n].toCharArray()[i].digitToInt()
                p = if (d == 0) {
                    p.first + 1 to p.second
                } else {
                    p.first to p.second + 1
                }
            }
            oxygenBits.add(p)
            carbonBits.add(p)
        }
        for (i in 0 until input[0].length) {
            val oBits = oxygenBits[i]
            val cBits = carbonBits[i]
            val commonOxygen = if (oBits.first > oBits.second) 0 else 1
            val commonCarbon = if (cBits.first > cBits.second) 0 else 1
            if (oxygen.size > 1) {
                oxygen = oxygen.filter {
                    val d = it.toCharArray()[i].digitToInt()
                    d == commonOxygen
                }
            }
            if (carbon.size > 1) {
                carbon = carbon.filter {
                    val d = it.toCharArray()[i].digitToInt()
                    d != commonCarbon
                }
            }
            if (i < input[0].length - 1) {
                val j = i + 1
                var o = 0 to 0
                var c = 0 to 0
                oxygen.forEach {
                    val d = it.toCharArray()[j].digitToInt()
                    o = if (d == 0) {
                        o.first + 1 to o.second
                    } else {
                        o.first to o.second + 1
                    }
                }
                oxygenBits[j] = o
                carbon.forEach {
                    val d = it.toCharArray()[j].digitToInt()
                    c = if (d == 0) {
                        c.first + 1 to c.second
                    } else {
                        c.first to c.second + 1
                    }
                }
                carbonBits[j] = c
            }
        }
        val oxygenRating = oxygen.first().toShort(2)
        val carbonRating = carbon.first().toShort(2)
        return oxygenRating * carbonRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val p1 = part1(testInput)
    println("part1_test: $p1")
    check(p1 == 198)

    val p2 = part2(testInput)
    println("part2_test: $p2")
    check(p2 == 230)

    val input = readInput("Day03")

    input.assertAllStringsSameLength()

    println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}



