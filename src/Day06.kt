fun main() {
    val lines = readInput("Day06_test")

//    println(lines)

    fun partOne(input: List<String>) {
        val times = input[0].split(" ").drop(1).filter { it.isNotBlank() }
        val distances = input[1].split(" ").drop(1).filter { it.isNotBlank() }
        println(times)
        println(distances)

        val waysToWin = List(times.size) { 0 }.toMutableList()

        times.forEachIndexed { idx, time ->
           val possibleTimesToHold = List(time.toInt()) {1 + it}
            println(possibleTimesToHold)
            val distanceToBeat = distances[idx]
            possibleTimesToHold.forEach { pth ->
                val dist = (time.toInt() - pth) * pth
                if (dist > distanceToBeat.toInt()) {
                    waysToWin[idx] = waysToWin[idx] + 1
                }
            }
        }
        println(waysToWin)
        println(waysToWin.reduce{acc, i -> acc * i})
    }
//    partOne(lines)
    fun partTwo(input: List<String>) {
        val times = input[0].split(" ").drop(1).filter { it.isNotBlank() }
        val distances = input[1].split(" ").drop(1).filter { it.isNotBlank() }
        println(times)
        println(distances)

        val waysToWin = List(times.size) { 0 }.toMutableList()

        times.forEachIndexed { idx, time ->
            val possibleTimesToHold = List(time.toInt()) {1 + it}
            println(possibleTimesToHold)
            val distanceToBeat = distances[idx]
            possibleTimesToHold.forEach { pth ->
                val dist = (time.toLong() - pth) * pth
                if (dist > distanceToBeat.toLong()) {
                    waysToWin[idx] = waysToWin[idx] + 1
                }
            }
        }
        println(waysToWin)
        println(waysToWin.reduce{acc, i -> acc * i})
    }
    partTwo(readInput("Day06_part2_test"))
}