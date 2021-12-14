import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int) {


    constructor(x: String, y: String) : this(x.trim().toInt(), y.trim().toInt())
}

data class Line(val start: Point, val end: Point) {
    constructor(list: List<Point>) : this(list.first(), list.last())

    fun isHorizontal(): Boolean = start.y == end.y
    fun isVertical(): Boolean = start.x == end.x
    fun isDiagonal(): Boolean = abs(start.x - end.x) == abs(start.y - end.y)

    fun interpolate(): List<Point> {
        val list = mutableListOf<Point>()
        when {
            isHorizontal() -> {
                val a = min(start.x, end.x)
                val b = max(start.x, end.x)
                for (x in a..b) {
                    list.add(Point(x, start.y))
                }
            }
            isVertical() -> {
                val a = min(start.y, end.y)
                val b = max(start.y, end.y)
                for (y in a..b) {
                    list.add(Point(start.x, y))
                }
            }
            isDiagonal() -> {
                val xRange = IntRange(min(start.x, end.x), max(start.x, end.x))
                val yRange = IntRange(min(start.y, end.y), max(start.y, end.y))

                val xReverse = start.x > end.x
                val yReverse = start.y > end.y

                val count = max(xRange.count(), yRange.count())
                val xList = xRange.toList().let {
                    if (xReverse) it.reversed() else it
                }
                val yList = yRange.toList().let {
                    if (yReverse) it.reversed() else it
                }
                for (n in 0 until count) {
                    list.add((Point(xList[n], yList[n])))
                }
            }
        }
        return list
    }
}

fun main() {

    val fileName = "Day05"

    fun part1(input: List<String>): Int {
        val counter = mutableMapOf<Point, Int>()

        input.forEach { vent ->
            val coords = vent.split("->")
                .map { p ->
                    val (x, y) = p.split(",")
                    Point(x, y)
                }.toList()
            val line = Line(coords)
            if (!line.isDiagonal()) {
                val allPoints = line.interpolate()
                allPoints.forEach {
                    val count = counter[it] ?: 0
                    counter[it] = count + 1
                }
            }
        }

        return counter.filter { it.value > 1 }.count()
    }

    fun part2(input: List<String>): Int {
        val counter = mutableMapOf<Point, Int>()

        input.forEach { vent ->
            val coords = vent.split("->")
                .map { p ->
                    val (x, y) = p.split(",")
                    Point(x, y)
                }.toList()
            val line = Line(coords)
            val allPoints = line.interpolate()
            allPoints.forEach {
                val count = counter[it] ?: 0
                counter[it] = count + 1
            }
        }

        return counter.filter { it.value > 1 }.count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("${fileName}_test")
    val p1 = part1(testInput)
    println("${fileName}_test.p1: $p1")
    check(p1 == 5)

    val p2 = part2(testInput)
    println("${fileName}_test.p2: $p2")
    check(p2 == 12)

    val input = readInput(fileName)

    println("${fileName}.p1: ${part1(input)}")
    println("${fileName}.p2: ${part2(input)}")
}
