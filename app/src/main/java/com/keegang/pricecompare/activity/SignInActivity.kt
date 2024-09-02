package com.keegang.pricecompare.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.keegang.pricecompare.R
import com.keegang.pricecompare.ui.theme.PriceCompareTheme

class SignInActivity : ComponentActivity() {
    private lateinit var signInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        signInLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val user = FirebaseAuth.getInstance().currentUser
                    handleSignInResult(this, user)
                }
            }
        val sign_in_with_google = getString(R.string.sign_in_with_google)

        setContent {
            PriceCompareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignInScreen(LocalContext.current, innerPadding,sign_in_with_google) {
                        startSignInFlow()
                    }
                }
            }
        }
    }

    private fun startSignInFlow() {
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(context: Context, user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

}

@Composable
fun SignInScreen(context: Context, innerPadding: PaddingValues,sign_in_with_google: String, onSignInClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onSignInClick) {
            Text(sign_in_with_google)
        }
    }
}