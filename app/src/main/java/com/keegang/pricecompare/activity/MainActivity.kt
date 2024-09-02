package com.keegang.pricecompare.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.keegang.pricecompare.R
import com.keegang.pricecompare.ui.theme.PriceCompareTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userName = FirebaseAuth.getInstance().currentUser?.displayName
        val welcome = getString(R.string.welcome)
        val app_name = getString(R.string.app_name)

        setContent {
            PriceCompareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        padding = Modifier.padding(innerPadding),
                        userName = userName ?: "Unknown User",
                        welcome = welcome,
                        tool_name = app_name
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(padding: Modifier, userName: String, welcome: String, tool_name: String) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(tool_name) },
                    navigationIcon = {
                        IconButton(modifier = Modifier.width(48.dp),onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            content = { innerPadding ->
                MainWindow(innerPadding)
            }
        )
    }
}
@Composable
fun MainWindow(innerPadding: PaddingValues){
    Box(modifier = Modifier
        .padding(0.dp)
        .fillMaxWidth(1f)
        .fillMaxHeight(1f)
        .background(MaterialTheme.colorScheme.background)
    )
}
@Composable
fun DrawerContent() {
    Box(modifier = Modifier
        .padding(0.dp)
        .fillMaxWidth(0.9f)
        .fillMaxHeight(1f)
        .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier //Main Box
            .padding(16.dp)
            .fillMaxSize(1f)
            ) {
            Box(modifier = Modifier //User profile
                .padding(0.dp)
                .fillMaxHeight(0.2f)
                .fillMaxWidth(1f)
                .background(Color.Red)
                ) {
            }
            Box(modifier = Modifier //Tool tab
                .padding(0.dp)
                .fillMaxHeight(1f)
                .fillMaxWidth(1f)
                .background(Color.Blue)
                ) {
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewMainScreen() {
    PriceCompareTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Main(
                padding = Modifier.padding(innerPadding),
                userName = "Preview User",
                welcome = "Welcome",
                tool_name = "PriceCompare"
            )
        }
    }
}
