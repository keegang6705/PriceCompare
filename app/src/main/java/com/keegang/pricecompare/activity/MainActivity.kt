package com.keegang.pricecompare.activity

import com.keegang.pricecompare.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth
import com.keegang.pricecompare.ui.theme.PriceCompareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userName = FirebaseAuth.getInstance().currentUser?.displayName
        val welcome = getString(R.string.welcome)

        setContent {
            PriceCompareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        padding = Modifier.padding(innerPadding),
                        userName = userName ?: "Unknown User",
                        welcome = welcome
                    )
                }
            }
        }
    }
}

@Composable
fun Main(padding: Modifier, userName: String,welcome: String) {
    Box(
        modifier = padding.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$welcome, $userName!")
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewMainScreen() {
    PriceCompareTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Main(
                padding = Modifier.padding(innerPadding),
                userName = "Preview User",
                welcome = "Welcome"
            )
        }
    }
}
