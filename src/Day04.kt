import kotlin.math.pow

fun main() {
    val testInput = readInput("Day04_test")

    fun partOne(lines: List<String>) {
        val points = mutableListOf<Int>()
        lines.forEach {line ->
            val parts = line.split(": ", " | ")
            val winningNumbers = parts[1].split(" ").filterNot { it.isEmpty() }
                   .map { it.toInt() }
            val scratchedNumbers = parts[2].split(" ").filterNot { it.isEmpty() }
                    .map { it.toInt() }
            points.add(2.0.pow(scratchedNumbers.intersect(winningNumbers).size - 1).toInt())
        }
        println(points.sum())
    }

//    partOne(testInput)

    fun partTwo(lines: List<String>) {
        val scratchCardCount = List(lines.size) { 1 }.toMutableList() // you start with 1 copy of each card
        lines.forEachIndexed { idx, line ->
            val parts = line.split(": ", " | ")
            val winningNumbers = parts[1].split(" ").filterNot { it.isEmpty() }
                    .map { it.toInt() }
            val scratchedNumbers = parts[2].split(" ").filterNot { it.isEmpty() }
                    .map { it.toInt() }

            val numberOfCopiedCardsAfter = scratchedNumbers.intersect(winningNumbers)
            List(scratchCardCount[idx]) {1}.forEach {
                var pos = idx
                numberOfCopiedCardsAfter.forEach {
                    pos++
                    scratchCardCount[pos] = (scratchCardCount[pos] + 1)
                }
            }
        }
        println(scratchCardCount.sum())
    }
    partTwo(testInput)
}