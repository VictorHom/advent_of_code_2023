import java.math.BigInteger

fun main() {
    val testInput = readInput("Day05_sample")

    fun partOne(lines: List<String>) {
        val resourceMap = mutableMapOf<String, MutableMap<Long, Long>>()
//        val mapOrder = listOf("soil", "fertilizer", "water", "light", "temperature", "humidity", "location")
//        lines.ch
        val chunks = lines.joinToString { it }.split(", ")
//        println(chunks.first().split("seeds:", " ").filterNot { it.isBlank() }.map { it.toBigInteger() })
        val seeds = chunks.first().split("seeds:", " ").filterNot { it.isBlank() }.map { it.toString() }
        println(seeds)
        val unparsedMapLines = lines.slice(1 until lines.size).joinToString { it }.split(", , ")
//        println(seeds)
//        println(unparsedMapLines)
        unparsedMapLines.forEach { line ->
            val parts = line.split(", ")
            println(parts[0])
            val resourceMapNames = parts[0].split("-to-").map { it.replace(" map:", "") }
            (parts.slice(2 until parts.size)).forEach {part ->
//                println(part)
                val range = part.split(" ").map { it.toLong() }
                val step = range[2].toInt()
                val destinationRange = List(step) { range[0] + it.toLong() }
                val startRange = List(step) { range[1] + it.toLong() }

                startRange.withIndex().forEach { (idx, startValue) ->
                    val key = if (resourceMapNames.last() == "") "soil" else resourceMapNames.last()
//                    println("$idx, $startValue")
                    if (resourceMap.containsKey(key)) {
                        resourceMap[key]?.set(startValue, destinationRange[idx])
                    } else {
//                        println("----")
//                        println(resourceMapNames)
//                        println("----")
                        resourceMap.set(key, mutableMapOf())
                    }
                }
            }

        }
//        println(resourceMap)
        val mapOrder = listOf("soil", "fertilizer", "water", "light", "temperature", "humidity", "location")
        val locationNumbers = seeds.map {

            var value = it
            mapOrder.forEach {resource ->
                println(value)
                value = (resourceMap[resource]?.get(value.toLong()) ?: value).toString()

            }
//            println(value)
            value
        }
        println("solution:")
        println(locationNumbers.min())
    }
    partOne(testInput)

    fun partTwo(lines: List<String>) {
    }
}