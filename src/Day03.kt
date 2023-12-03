
data class Point2D(val x: Int, val y: Int)
fun main() {
    val testInput = readInput("Day03_test")
    fun partOne(lines: List<String>) {
        var sumOfPartNumbers = 0

        lines.forEachIndexed { y, line ->
            var x = 0
            while (x < line.length) {
                if (!line[x].isDigit()) {
                    x++
                    continue
                }

                val number = line.substring(x).takeWhile { it.isDigit() }
                val top = (x until x + number.length).map { Point2D(it, y - 1) }
                val bottom = (x until x + number.length).map { Point2D(it, y + 1) }
                val left = (y - 1..y + 1).map { Point2D(x - 1, it) }
                val right = (y - 1..y + 1).map { Point2D(x + number.length, it) }

                listOf(top, bottom, left, right).flatten()
                    .filter { it.y in lines.indices && it.x in line.indices } // remove out of bounds
                    .forEach { point ->
//                        no symbols that are just next to each other
                        if (lines[point.y][point.x] != '.') {
                            sumOfPartNumbers += number.toInt()
                        }
                    }
                x += number.length
            }
        }
        println(sumOfPartNumbers)
    }
//    partOne(testInput)

    fun partTwo(lines: List<String>) {
        val asterisks = mutableMapOf<Point2D, MutableList<Int>>()

        lines.forEachIndexed { y, line ->
            var x = 0
            while (x < line.length) {
                if (!line[x].isDigit()) {
                    x++
                    continue
                }

                val number = line.substring(x).takeWhile { it.isDigit() }
                val top = (x until x + number.length).map { Point2D(it, y - 1) }
                val bottom = (x until x + number.length).map { Point2D(it, y + 1) }
                val left = (y - 1..y + 1).map { Point2D(x - 1, it) }
                val right = (y - 1..y + 1).map { Point2D(x + number.length, it) }

                listOf(top, bottom, left, right).flatten()
                    .filter { it.y in lines.indices && it.x in line.indices } // remove out of bounds
                    .forEach { point ->
                        if (lines[point.y][point.x] == '*') {
                            asterisks.getOrPut(point) { mutableListOf() }.add(number.toInt())
                        }
                    }
                x += number.length
            }
        }
        println(asterisks.filterValues {
            it.size == 2
        }.values.sumOf {
            it.reduce { acc, v ->
               v * acc
            }
        })
    }
    partTwo(testInput)
}