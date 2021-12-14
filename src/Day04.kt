data class BingoSquare(var number: Int, var marked: Boolean = false)
class BingoCard(input: List<String>) {
    private val squares = Array(5) { Array(5) { BingoSquare(0) } }

    init {
        input.forEachIndexed { row, line ->
            line.split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }
                .forEachIndexed { col, number ->
                    squares[row][col].number = number
                }
        }
    }

    fun markNumber(number: Int) {
        squares.forEach { square ->
            square.forEach {
                if (it.number == number) {
                    it.marked = true
                    return
                }
            }
        }
    }

    fun isWinner(): Boolean {
        for (n in 0..4) {
            if (squares[n][0].marked && squares[n][1].marked && squares[n][2].marked && squares[n][3].marked && squares[n][4].marked) {
                return true
            }
            if (squares[0][n].marked && squares[1][n].marked && squares[2][n].marked && squares[3][n].marked && squares[4][n].marked) {
                return true
            }
        }
        return false
    }

    fun score(lastNumber: Int): Int {
        if (!isWinner()) {
            return 0
        }

        val totalUnmarked = squares.sumOf { row ->
            row.filter { it.marked.not() }.sumOf { it.number }
        }

        return totalUnmarked * lastNumber
    }
}

fun main() {

    fun bingoNumbers(input: String) = input.split(",").map { it.toInt() }

    fun bingoCards(input: List<String>): List<BingoCard> {
        val cards = mutableListOf<BingoCard>()
        var card = mutableListOf<String>()
        var n = 0
        while (n < input.size) {
            val line = input[n]
            if (line.isNotEmpty()) {
                card.add(line)
            } else {
                cards.add(BingoCard(card))
                card = mutableListOf()
            }
            n++
        }
        if (card.size > 0) {
            cards.add(BingoCard(card))
        }
        return cards
    }

    fun part1(input: List<String>): Int {
        val numbers = bingoNumbers(input.first())
        val cards = bingoCards(input.subList(2, input.size))

        var winner: BingoCard? = null
        var winningNumber = 0

        numbers.forEachIndexed { round, number ->
            if (winner == null) {
                cards.forEach { card ->
                    card.markNumber(number)
                    if (card.isWinner()) {
                        winner = card
                        winningNumber = number
                        return@forEachIndexed
                    }
                }
            }
        }

        return winner?.score(winningNumber) ?: 0
    }

    fun part2(input: List<String>): Int {
        val numbers = bingoNumbers(input.first())
        val cards = bingoCards(input.subList(2, input.size))

        var winners = mutableSetOf<BingoCard>()
        var winner: BingoCard? = null
        var winningNumber = 0

        numbers.forEachIndexed { _, number ->
            if (winner == null) {
                cards.forEach { card ->
                    if (card.isWinner().not()) {
                        card.markNumber(number)
                    }
                    if (card.isWinner() && winners.contains(card).not()) {
                        winners.add(card)
                        if (winners.size == cards.size) {
                            winner = card
                            winningNumber = number
                            return@forEachIndexed
                        }
                    }
                }
            }
        }

        return winner?.score(winningNumber) ?: 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val p1 = part1(testInput)
    println("part1_test: $p1")
    check(p1 == 4512)

    val p2 = part2(testInput)
    println("part2_test: $p2")
    check(p2 == 1924)

    val input = readInput("Day04")

    println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}
