package com.example.lykkehjuleteftercrash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.lykkehjuleteftercrash.navigation.ApNavigator
import com.example.lykkehjuleteftercrash.ui.theme.WOFViewModel
import com.example.lykkehjuleteftercrash.ui.theme.LykkehjuletEfterCrashTheme
import com.example.lykkehjuleteftercrash.ui.theme.paleGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LykkehjuletEfterCrashTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = paleGreen
                ) {
                    val navController = rememberNavController()
                    val viewModel = WOFViewModel()
                    ApNavigator(navController = navController, WOFViewModel =viewModel )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LykkehjuletEfterCrashTheme {
        Greeting("Android")
    }
}