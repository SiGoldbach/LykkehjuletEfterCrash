package com.example.lykkehjuleteftercrash.model

import androidx.compose.ui.graphics.Color

class LetterCard(char: Char) {
    var char: Char = char
    var color = Color.Black

    fun changeColor() {
        color = Color.White
    }

}