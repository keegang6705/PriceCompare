package com.keegang.pricecompare.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.keegang.pricecompare.ui.theme.PriceCompareTheme


class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PriceCompareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WelcomeScreen(LocalContext.current, innerPadding)
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(context: Context, innerPadding: PaddingValues) {
    Box(modifier = Modifier.padding(innerPadding))
    //val googleSignInClient = remember { GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN) }

    val account = GoogleSignIn.getLastSignedInAccount(context)
    val isSignedIn = account != null

    LaunchedEffect(isSignedIn) {
        if (isSignedIn) {
            context.startActivity(Intent(context, MainActivity::class.java))
        } else {
            context.startActivity(Intent(context, SignInActivity::class.java))
        }
    }
}