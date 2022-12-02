package com.example.lykkehjuleteftercrash.model


data class GameUiState(
    var lives: Int = 5,
    var points: Int = 0,
    var word: List<LetterCard> = listOf(),
    var wordAsString: String = "",
    var category: String = "",
    var concealedWord: List<LetterCard> = listOf(),
    var gameStatus: State = State.SPIN,
    var potentialPoints: Int = 0,
    var statusText: String = "SPIN",
    val keyboardLetters: List<KeyboardLetter> = listOf(
        KeyboardLetter('A'), KeyboardLetter('B'), KeyboardLetter('C'), KeyboardLetter('D'),
        KeyboardLetter('E'), KeyboardLetter('F'), KeyboardLetter('G'), KeyboardLetter('H'),
        KeyboardLetter('I'), KeyboardLetter('J'), KeyboardLetter('K'), KeyboardLetter('L'),
        KeyboardLetter('M'), KeyboardLetter('N'), KeyboardLetter('O'), KeyboardLetter('P'),
        KeyboardLetter('Q'), KeyboardLetter('R'), KeyboardLetter('S'), KeyboardLetter('T'),
        KeyboardLetter('U'), KeyboardLetter('V'), KeyboardLetter('W'), KeyboardLetter('X'),
        KeyboardLetter('Y'), KeyboardLetter('Z')

    ),
)