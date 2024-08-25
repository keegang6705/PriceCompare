package com.keegang.pricecompare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.keegang.pricecompare.ui.theme.PriceCompareTheme
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PriceCompareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        padding = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(padding :Modifier) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "screen1") {
        composable("screen1") { Screen1(navController) }
        composable("screen2") { Screen2(navController) }
    }
}

@Composable
fun Screen1(navController: NavController) {
    Button(onClick = { navController.navigate("screen2") }) {
        Text("Go to Screen 2")
    }
}

@Composable
fun Screen2(navController: NavController) {
    Button(onClick = { navController.navigate("screen1") }) {
        Text("Back to Screen 1")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    PriceCompareTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Main(
                padding = Modifier.padding(innerPadding)
            )
        }
    }
}

