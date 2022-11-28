package com.example.lykkehjuleteftercrash.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lykkehjuleteftercrash.R
import com.example.lykkehjuleteftercrash.navigation.Destination


@Composable
fun WelcomeScreen(navController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp, shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StdText(string = "Welcome to the wheel of fortune")
            StdSubText(string = "Categories are: ")
            StdSubText(string = "Food")
            StdSubText(string = "Countries")
            StdSubText(string = "Cities")
            Image(
                painter = painterResource(id = R.drawable.lykkehjul2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit

            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    // viewModel.resetGame()
                    navController.navigate(Destination.Guess.route)
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                StdText(string = "Start The Game")

            }


        }


    }


}

@Composable
fun StdSubText(string: String) {
    Text(text = string, color = Color.Black, fontSize = 16.sp)

}