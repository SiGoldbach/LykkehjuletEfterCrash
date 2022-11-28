package com.example.lykkehjuleteftercrash.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.lykkehjuleteftercrash.data.GameData
import com.example.lykkehjuleteftercrash.ui.theme.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*

/**
 * Here the player with holds the players info is private since on,
 * The business layer in the viewModel should be able to alter it.
 */

class WOFViewModel : ViewModel() {
    private val gameUiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = gameUiState.asStateFlow()
    // val list= stringArrayResource(id = R.values)

    init {
        resetGame()
    }


    /**
     * This is the function that should be called every time a new game is started
     * Because it resets all the values that needs to be reset.
     */
    private fun resetGame() {
        println("Getting to init")



        gameUiState.update { currentState ->
            currentState.copy(
                points = 0,
                lives = 5,
                keyboardLetters = GameUiState().keyboardLetters,
                gameStatus = State.SPIN,
                statusText = "SPIN"
            )

        }
        println("Crashing before generateWord")
        generateWord()


    }

    fun resetTester() {
        resetGame()
    }

    private fun generateWord() {
        val wordAndCategory: Array<String> = GameData().giveCategoryAndWord()
        val word: String = wordAndCategory[0]
        println("Crashing before makeStartState")
        makeStartState(word)
        println("Crashing after makeStartState")
        gameUiState.update { currentState ->
            currentState.copy(
                category = wordAndCategory[1]
            )
        }


    }

    private fun makeStartState(word: String) {
        println("Crashing before making arrays")
        val hiddenWord: List<LetterCard> = List(18) { LetterCard(' ') }
        val realWord: List<LetterCard> = List(18) { LetterCard(' ') }
        println("Making arrays properly")
        var start = 2
        println(realWord.size)
        println(word)
        for (char in word) {
            println(1)
            realWord[start].char = char
            start++
        }
        println("After loop")
        var counter = 0
        while (counter < hiddenWord.size) {
            if (realWord[counter].char != ' ') {
                hiddenWord[counter].color = Color.White
                println(counter)
            }
            counter++


        }
        println("Hidden word has size: " + hiddenWord.size)
        gameUiState.update { currentState ->
            currentState.copy(
                concealedWord = hiddenWord,
                word = realWord, wordAsString = word
            )
        }
        println("       This should be 15: " + uiState.value.concealedWord.size)


    }


    /**
     * This is a function for losing 1 life.
     */

    private fun loseLife() {
        gameUiState.update { currentState -> currentState.copy(lives = gameUiState.value.lives - 1) }

    }

    fun spinTheWheel() {
        if (gameUiState.value.gameStatus != State.SPIN) {
            return
        }
        val potentialPointsList: List<Int> = GameData().generateRewardList()
        val rand: Int = Random().nextInt(potentialPointsList.size)
        val potentialPoint = potentialPointsList[rand]
        if (potentialPoint == -1) {
            updateStateMoney(0)
            gameUiState.update { GameUiState ->
                GameUiState.copy(
                    statusText = "Oh no you went bankrupt spin again"
                )
            }
            return
        }
        val status = "$potentialPoint Pr. letter"
        gameUiState.update { GameUiState ->
            GameUiState.copy(
                potentialPoints = potentialPoint,
                statusText = status,
                gameStatus = State.GUESS
            )

        }
    }

    /**
     * Updates the players money if it is minus 1 it means bankrupt.
     */
    private fun freeMoney(updateMoney: Int) {
        println("In free money")
        println(uiState.value.points)

        if (updateMoney == -1) {
            updateStateMoney(0)
        } else {
            updateStateMoney(gameUiState.value.points + updateMoney)
        }

    }

    private fun updateStateMoney(newBalance: Int) {
        gameUiState.update { GameUiState ->
            GameUiState.copy(
                points = newBalance
            )
        }
    }

    fun guessLetter(char: Char): Boolean {
        var earnings = 0
        if (gameUiState.value.gameStatus != State.GUESS) {
            return false
        }
        println(char)
        val hiddenWord: MutableList<LetterCard> = uiState.value.concealedWord.toMutableList()
        val realWord: List<LetterCard> = uiState.value.word

        var wrongGuess = true
        var counter = 0
        while (counter < realWord.size) {
            if (realWord[counter].char == char) {
                earnings++
                wrongGuess = false
                println("Making changes")
                val letter = LetterCard(char)
                letter.changeColor()
                hiddenWord[counter] = letter

            }




            counter++

        }
        println("Getting out of the loop")
        val updateKeyboardList: MutableList<KeyboardLetter> =
            uiState.value.keyboardLetters.toMutableList()
        var counter2 = 0
        while (counter2 < updateKeyboardList.size) {
            if (updateKeyboardList[counter2].char == char) {
                val newKeyBoardLetter = KeyboardLetter(char)
                newKeyBoardLetter.reveal()

                updateKeyboardList[counter2] = newKeyBoardLetter
            }
            println("In second loop")

            counter2++
        }
        if (wrongGuess) {
            loseLife()

        }
        freeMoney(earnings * gameUiState.value.potentialPoints)

        gameUiState.update { currentState ->
            currentState.copy(
                concealedWord = hiddenWord,
                keyboardLetters = updateKeyboardList,
                gameStatus = State.SPIN,
                statusText = "SPIN"
            )
        }
        checkIfGameIsOver()



        return true
    }

    private fun checkIfGameIsOver() {
        if (gameUiState.value.lives == 0) {
            gameUiState.update { GameUiState ->
                GameUiState.copy(
                    gameStatus = State.LOST,
                    statusText = "Game over "
                )
            }

            return
        }
        var counter = 0
        while (counter < gameUiState.value.concealedWord.size) {
            if (gameUiState.value.concealedWord[counter].char != gameUiState.value.word[counter].char) {
                println("$counter Letter does not match")
                return
            }
            counter++
        }
        gameUiState.update { GameUiState ->
            GameUiState.copy(
                gameStatus = State.WON,
                statusText = "You won "
            )
        }


    }
}