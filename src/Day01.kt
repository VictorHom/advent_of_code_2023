import java.lang.Double.parseDouble


fun main() {
    fun parseDoubleSafe(s: String): Int {
        return try {
            parseDouble(s).toInt()
        } catch (e: NumberFormatException) {
            -1
        }
    }

    val forwardNums = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val backwardNums = listOf("eno", "owt", "eerht", "ruof", "evif", "xis", "neves", "thgie", "enin")
    val forwardN = arrayOf(1, 2, 3,4,5,6, 7,8 ,9)

    fun forwardSpelledNumber(line: String, idx: Int): Int {
        for (num in forwardNums) {
            if (line.substring(idx).indexOf(num) === 0) {
                return forwardN[forwardNums.indexOf(num)];
            }
        }
        return -1;
    }

    fun backwardSpelledNumber(line: String, idx: Int): Int {
        for (num in backwardNums) {
            if (line.substring(idx).indexOf(num) === 0) {
                val n = forwardN[backwardNums.indexOf(num)];
                return n;
            }
        }
        return -1
    }

    fun specializeParse(s: String, line: String, idx: Int, numberChecker: (line: String, idx: Int) -> Int): Int {
        val n = numberChecker(line, idx)
        if (n > 0) {
            return n;
        }
        return try {
            return parseDouble(s).toInt()

        } catch (e: NumberFormatException) {
            return -1
        }
    }

    val testInput = readInput("Day01_test")

    fun partOne(testInput: List<String>) {
        var calibrationSum = 0;
        for (line in testInput) {
            var calibration = "";
            for (c in line) {
                if (parseDoubleSafe(c.toString()) > -1.0) {
                    calibration = parseDoubleSafe(c.toString()).toString()
                    break
                }
            }

            for (i in line.reversed()) {
                if (parseDoubleSafe(i.toString()) > -1.0) {
                    calibration += parseDoubleSafe(i.toString()).toString()
                    break
                }

            }
            calibrationSum += parseDouble(calibration).toInt()
        }
        println(calibrationSum)
    }

    // partOne()

    fun partTwo(testInput: List<String>) {
        var calibrationSum = 0;
        for (line in testInput) {
            var calibration = "";
            for ((idx, c) in line.withIndex()) {
                val n = specializeParse(c.toString(), line, idx) { idx, line -> forwardSpelledNumber(idx, line) }
                if (n > 0) {
                    calibration = n.toString()
                    break
                }
            }

            for ((idx, c) in line.reversed().withIndex()) {
                val n = specializeParse(c.toString(), line.reversed(), idx) { idx, line -> backwardSpelledNumber(idx, line) }
                if (n > 0) {
                    calibration += n.toString()
                    break
                }
            }
            println(calibration)
            calibrationSum += parseDouble(calibration).toInt()
        }
        println(calibrationSum)
    }

    partTwo(testInput)

}
