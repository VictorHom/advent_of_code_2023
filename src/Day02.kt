fun main() {

    val testInput = readInput("Day02_test")
//    println(testInput)

    fun meetsCheck(set: Map<String, Int>): Boolean {
        var redCheck = true;
        var blueCheck = true;
        var greenCheck = true;
       set.forEach {group ->
           val color = group.key
           val num = group.value
           println("$color, $num")
           if (color == "red" && num > 12) {
              redCheck = false
           }
           if (color == "green" && num > 13) {
              greenCheck = false;
           }
           if (color == "blue" && num > 14) {
               blueCheck = false;
           }
       }
       return redCheck && blueCheck && greenCheck
    }


    // only 12 red cubes, 13 green cubes, and 14 blue cubes
    //    gameMap = {sets: [colorRevealMap]}
    fun partOne(textInput: List<String> ) {
        val games = mutableMapOf<String, MutableList<Map<String, Int>>>();
        for (line in textInput) {
            val gameId = line.substringAfter("Game ").substringBefore(':')
            val gameSets = line.substringAfter(": ").split("; ")
            games[gameId] = mutableListOf();
            for(set in gameSets) {
                val cubesShown = set.split(", ");
                val shownMap = mutableMapOf<String, Int>();
                for (shown in cubesShown) {
                    val numberAndColor = shown.split(" ");
                    val number = numberAndColor[0].toInt()
                    val color = numberAndColor[1]
                    shownMap[color] = number;
                }
                games[gameId]?.add(shownMap);
            }

        }
//        println(games)

        val idsThatMatch = mutableListOf<Int>()
        games.forEach { game ->
            val gameId = game.key.toInt()
            val sets = game.value
            var check = true;
            for (set in sets) {
                val setStatus = meetsCheck(set)
                if (setStatus === false) {
                    println("false setting")
                    check = false
                    break;
                }
            }
            if (check) {
                idsThatMatch.add(gameId)
            }
        }
        println(idsThatMatch.sum())

    }


    fun partTwo(textInput: List<String>) {
        val games = mutableMapOf<String, MutableList<Map<String, Int>>>();
        for (line in textInput) {
            val gameId = line.substringAfter("Game ").substringBefore(':')
            val gameSets = line.substringAfter(": ").split("; ")
            games[gameId] = mutableListOf();
            for(set in gameSets) {
                val cubesShown = set.split(", ");
                val shownMap = mutableMapOf<String, Int>();
                for (shown in cubesShown) {
                    val numberAndColor = shown.split(" ");
                    val number = numberAndColor[0].toInt()
                    val color = numberAndColor[1]
                    shownMap[color] = number;
                }
                games[gameId]?.add(shownMap);
            }

        }
        val minProducts = mutableListOf<Int>()
        games.forEach { game ->
            val gameId = game.key.toInt()
            val sets = game.value
            val minRequired = mutableMapOf("blue" to 0, "red" to 0, "green" to 0)
            for (set in sets) {
               set.forEach {
                   val color = it.key
                   val num = it.value
                   minRequired[color] = if (num > minRequired[color]!!) num else minRequired[color]!!
               }
            }
            minProducts.add(minRequired.values.reduce(Int::times))
        }

        println(minProducts.sum())
    }
//    partOne(testInput)
    partTwo(testInput)
}