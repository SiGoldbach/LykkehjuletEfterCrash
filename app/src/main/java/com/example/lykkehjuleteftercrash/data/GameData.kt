package com.example.lykkehjuleteftercrash.data

import java.util.*

class GameData : IGameRepository {
    private var cities: Array<String> = arrayOf(
        "LONDON",
        "PARIS",
        "MIAMI",
        "AUSTIN",
        "NEW YORK",
        "SAN DIEGO",
        "MANCHESTER",
        "MOSCOW",
        "SYDNEY",
        "BANGKOK",
        "BERLIN",
        "TOKYO"

    )
    private var food: Array<String> = arrayOf(
        "CHEESE",
        "PASTA",
        "LASAGNA",
        "MEATLOAF",
        "HAM",
        "CORNBREAD",
        "CHEESECAKE",
        "OATMEAL",
        "BURGER",
        "PIZZA",
        "BUTTER CHICKEN",
        "TURKEY"

    )
    private var country: Array<String> = arrayOf(
        "DENMARK",
        "SWEDEN",
        "GERMANY",
        "RUSSIA",
        "CUBA",
        "SPAIN",
        "PORTUGAL",
        "SOUTH AFRICA",
        "COLUMBIA",
        "MEXICO",
        "THAILAND",
        "NEW ZEALAND"
    )

    private var rewardList: List<Int> = listOf(
        -1,
        1500,
        800,
        100,
        500,
        600,
        500,
        800,
        500,
        1000,
        100,
        300,
        800,
        1000,
        500,
        600,
        500,
        800,
        500
    )


    /**
     * Returns a word and a category for the given word this, could be changed by another method.
     * Since the viewModel will just need a word and category. so one could change to another implementation.
     */

    override fun giveCategoryAndWord(): Array<String> {
        val wordAndCategory: Array<String> = arrayOf("Word", "Category")
        val arrayToPickFrom: Array<String>

        when (Random().nextInt(3)) {
            0 -> {
                wordAndCategory[1] = "Cities"
                arrayToPickFrom = this.cities
            }
            1 -> {
                wordAndCategory[1] = "Food"
                arrayToPickFrom = this.food
            }
            else -> {
                wordAndCategory[1] = "Country"
                arrayToPickFrom = this.country

            }
        }
        val rand2: Int = Random().nextInt(arrayToPickFrom.size)
        wordAndCategory[0] = arrayToPickFrom[rand2]


        return wordAndCategory

    }

    override fun generateRewardList(): List<Int> {
        return rewardList
    }


}
