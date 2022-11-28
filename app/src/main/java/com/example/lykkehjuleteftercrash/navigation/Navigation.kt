package com.example.lykkehjuleteftercrash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lykkehjuleteftercrash.ui.theme.WOFViewModel
import com.example.lykkehjuleteftercrash.ui.theme.screens.InGameScreen
import com.example.lykkehjuleteftercrash.ui.theme.screens.WelcomeScreen

@Composable
fun ApNavigator(navController: NavHostController, WOFViewModel: WOFViewModel) {

    NavHost(
        navController = navController, startDestination = Destination.Welcome.route
    ) {
        composable(Destination.Welcome.route) {
            WelcomeScreen(
                navController = navController
            )

        }
        composable(Destination.Guess.route) {
            InGameScreen(
                navController = navController,
                WOFViewModel = WOFViewModel
            )
        }
        /*composable(Destination.Detail.route) { backStackEntry ->
            val elementId = backStackEntry.arguments?.getString("elementId")
            if(elementId == null){
                Toast.makeText(ctx, "No elementId found", Toast.LENGTH_SHORT).show()
            } else  {
                DetailScreen(elementId = elementId.toInt())
            }*/

    }
}