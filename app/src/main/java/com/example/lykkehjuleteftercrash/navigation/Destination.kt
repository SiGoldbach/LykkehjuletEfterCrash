package com.example.lykkehjuleteftercrash.navigation

sealed class Destination(val route: String) {
    object Welcome : Destination("welcomeScreen")
    object Guess : Destination("guessLetterScreen")
    object Victory : Destination("victoryScreen")

}