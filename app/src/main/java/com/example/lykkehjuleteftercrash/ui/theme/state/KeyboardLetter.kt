package com.example.lykkehjuleteftercrash.ui.theme.state

import androidx.compose.ui.graphics.Color
import com.example.lykkehjuleteftercrash.ui.theme.paleGreen
import com.example.lykkehjuleteftercrash.ui.theme.red

class KeyboardLetter(char: Char) {
    var char: Char
    var alive: Boolean = true
    var color: Color = red

    init {
        this.char = char
    }

    fun reveal() {
        this.alive = false
        this.color = paleGreen
        this.char = ' '
    }


}