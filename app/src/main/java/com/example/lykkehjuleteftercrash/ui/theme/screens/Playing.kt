package com.example.lykkehjuleteftercrash.ui.theme.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.lykkehjuleteftercrash.R
import com.example.lykkehjuleteftercrash.ui.theme.WOFViewModel
import com.example.lykkehjuleteftercrash.ui.theme.state.KeyboardLetter
import com.example.lykkehjuleteftercrash.ui.theme.state.LetterCard

/**
 * Here is the screen where the word has to be guessed
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InGameScreen(navController: NavHostController, WOFViewModel: WOFViewModel) {
    val gameUiState by WOFViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(height = 60.dp, width = 60.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.transparent_heart_pixel),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit

                )
            }
            StdText(
                string = "  " + gameUiState.lives + "     "
            )



            Box(modifier = Modifier.size(height = 60.dp, width = 60.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.coin_image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit

                )
            }

            StdText(string = "  " + gameUiState.points)


        }

        StdText("Category is: " + gameUiState.category)
        //StdText(string = "Word is: " + gameUiState.wordAsString)
        Spacer(modifier = Modifier.height(10.dp))
        //Spacer(modifier = Modifier.weight(1.0F))


        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 40.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            content = {
                println("Concealed word has size: " + gameUiState.concealedWord.size)
                items(gameUiState.concealedWord) { unit ->
                    println("Making a letter ")
                    HiddenLettersComposer(
                        letterCard = unit,
                        WOFViewModel = WOFViewModel
                    )
                }

            }
        )
        Row(
            modifier = Modifier
                .fillMaxHeight(0.3F)
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
            ) {
                StdText(
                    string = gameUiState.statusText
                )

            }
            Button(
                onClick = { WOFViewModel.spinTheWheel() },
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxHeight(), shape = RoundedCornerShape(20.dp)
            ) {
                StdText(string = "Spin the wheel")

            }


        }
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 40.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            content = {
                items(gameUiState.keyboardLetters) { unit ->
                    println("Making alphabet letters")
                    LetterComposer(keyboardLetter = unit, WOFViewModel = WOFViewModel)
                }

            }
        )

        Button(onClick = { WOFViewModel.resetTester() }) {
            StdText(string = "Reset Game")

        }

    }


}

@Composable
fun LetterComposer(keyboardLetter: KeyboardLetter, WOFViewModel: WOFViewModel) {
    Card(
        backgroundColor = keyboardLetter.color, modifier = Modifier
            .padding(3.dp)
            .clickable(
                enabled = keyboardLetter.alive,
                onClick = { WOFViewModel.guessLetter(keyboardLetter.char) })
    )
    {
        Text(
            text = keyboardLetter.char.toString(),
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(5.dp),
        )

    }
}

@Composable
fun HiddenLettersComposer(letterCard: LetterCard, WOFViewModel: WOFViewModel) {
    Card(
        backgroundColor = letterCard.color, modifier = Modifier
            .padding(3.dp)
            .size(40.dp, 60.dp)
    ) {
        Text(
            text = letterCard.char.toString(),
            color = Color.Black,
            fontSize = 24.sp,
            modifier = Modifier.padding(5.dp),
        )

    }

}

@Composable
fun StdText(string: String) {
    Text(text = string, color = Color.Black, fontSize = 24.sp)

}
