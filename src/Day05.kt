import java.math.BigInteger

fun main() {
    val testInput = readInput("Day05_test")

    fun partOne(lines: List<String>) {
        val resourceMap = mutableMapOf<String, MutableList<Triple<Long,Long, Int>>>()
        val chunks = lines.joinToString { it }.split(", ")
        val seeds = chunks.first().split("seeds:", " ").filterNot { it.isBlank() }.map { it.toLong() }

        val unparsedMapLines = lines.slice(1 until lines.size).joinToString { it }.split(", , ")
        println(unparsedMapLines)
        unparsedMapLines.forEachIndexed { idx, line ->
            var parts = line.split(", ")
            // parsing shenanigans
            if (idx == 0) {
                parts = parts.slice(1 until parts.size)
            }
            val resourceMapNames = parts[0].split("-to-").map { it.replace(" map:", "") }
            println(parts.slice(1 until parts.size))
            (parts.slice(1 until parts.size)).forEach { part ->
                val range = part.split(" ").map { it.toLong() }
                val step = range[2].toInt()
                val key = if (resourceMapNames.last() == "") "soil" else resourceMapNames.last()

                if (resourceMap.containsKey(key)) {
                    resourceMap[key]?.add(Triple(range[1], range[0], step))
                } else {
                    resourceMap.set(key, mutableListOf(Triple(range[1], range[0], step)))
                }
            }

        }
        println(resourceMap)
//        println(resourceMap)
        val mapOrder = listOf("soil", "fertilizer", "water", "light", "temperature", "humidity", "location")
        val locationNumbers = seeds.map {
            var value = it
            mapOrder.forEach {resource ->
                val r = resourceMap[resource]?.find { value > it.first && (value <= (it.first + it.third -1)) }
                println(r)
                if (r != null) {
//                    println("$r.third $value $r.first")
                    value = r.second + (value - r.first)
                    println("value $value")
//                    println("here? $value ${r.second + (value - r.first)}")
                } else {
                   println("in else $value")
                }

            }
            println("by here $value")
            value
        }
        println("solution:")
        println(locationNumbers.min())
    }
//    partOne(testInput)

    fun partTwo(lines: List<String>) {
        val resourceMap = mutableMapOf<String, MutableList<Triple<Long,Long, Int>>>()
        val chunks = lines.joinToString { it }.split(", ")

        val foo = chunks.first().split("seeds:", " ").filterNot { it.isBlank() }.map { it.toLong() }.windowed(2).map {chunk -> List(chunk[1].toInt()){ chunk[0] + it }}.flatten()
        val seeds = foo

        val unparsedMapLines = lines.slice(1 until lines.size).joinToString { it }.split(", , ")
        println(unparsedMapLines)
        unparsedMapLines.forEachIndexed { idx, line ->
            var parts = line.split(", ")
            // parsing shenanigans
            if (idx == 0) {
                parts = parts.slice(1 until parts.size)
            }
            val resourceMapNames = parts[0].split("-to-").map { it.replace(" map:", "") }
            println(parts.slice(1 until parts.size))
            (parts.slice(1 until parts.size)).forEach { part ->
                val range = part.split(" ").map { it.toLong() }
                val step = range[2].toInt()
                val key = if (resourceMapNames.last() == "") "soil" else resourceMapNames.last()

                if (resourceMap.containsKey(key)) {
                    resourceMap[key]?.add(Triple(range[1], range[0], step))
                } else {
                    resourceMap.set(key, mutableListOf(Triple(range[1], range[0], step)))
                }
            }

        }
        println(resourceMap)
//        println(resourceMap)
        val mapOrder = listOf("soil", "fertilizer", "water", "light", "temperature", "humidity", "location")
        val locationNumbers = seeds.map {
            var value = it
            mapOrder.forEach {resource ->
                val r = resourceMap[resource]?.find { value > it.first && (value <= (it.first + it.third -1)) }
                println(r)
                if (r != null) {
//                    println("$r.third $value $r.first")
                    value = r.second + (value - r.first)
                    println("value $value")
//                    println("here? $value ${r.second + (value - r.first)}")
                } else {
                    println("in else $value")
                }

            }
            println("by here $value")
            value
        }
        println("solution:")
        println(locationNumbers.min())

    }
    partTwo(testInput)
}