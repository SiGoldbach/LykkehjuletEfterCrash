package com.example.lykkehjuleteftercrash.data

interface IGameRepository {
    fun giveCategoryAndWord(): Array<String>

    /**
     * In this method minus -1 will be understood as bankrupt.
     * This function only operates in point changes and nothing else.
     */
    fun generateRewardList(): List<Int>

}