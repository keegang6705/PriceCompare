package com.keegang.pricecompare.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.keegang.pricecompare.R
import com.keegang.pricecompare.ui.theme.PriceCompareTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appName = getString(R.string.app_name)

        setContent {
            PriceCompareTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        innerPadding = innerPadding,
                        toolName = appName
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(innerPadding: PaddingValues, toolName: String) {
    Box(modifier = Modifier.padding(innerPadding))
    FirebaseApp.initializeApp(LocalContext.current)
    val userName = FirebaseAuth.getInstance().currentUser?.displayName.toString()
    val userProfile = FirebaseAuth.getInstance().currentUser?.photoUrl.toString()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(userName = userName, profileImageUrl = userProfile)
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(toolName) },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.width(48.dp),
                            onClick = { scope.launch { drawerState.open() } }) {
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
fun MainWindow(innerPadding: PaddingValues) {
    Box(modifier = Modifier.padding(innerPadding))
    Box(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(MaterialTheme.colorScheme.background)
    )
}

@Composable
fun DrawerContent(userName: String, profileImageUrl: String) {
    Box(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth(0.9f)
            .fillMaxHeight(1f)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier //Main Box
                .padding(16.dp)
                .fillMaxSize(1f)
        ) {
            Row(
                modifier = Modifier //User profile card
                    .padding(0.dp)
                    .fillMaxHeight(0.15f)
                    .fillMaxWidth(1f)
            ) {
                Box(
                    modifier = Modifier //User profile picture container
                        .padding(0.dp)
                        .fillMaxHeight(1f)
                        .aspectRatio(1f)

                ) {
                    Box(
                        modifier = Modifier//User profile picture
                            .padding(20.dp)
                            .fillMaxHeight(1f)
                            .aspectRatio(1f)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = profileImageUrl),
                            contentDescription = "User Profile Picture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .clip(CircleShape)
                        )
                    }
                }
                Box(
                    modifier = Modifier //User name container
                        .padding(0.dp)
                        .fillMaxHeight(1f)
                        .fillMaxWidth(1f)
                ) {
                    Box(
                        modifier = Modifier //User name
                            .padding(0.dp, 30.dp)
                            .fillMaxHeight(1f)
                            .fillMaxWidth(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = userName,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier //Tool tab
                    .padding(0.dp)
                    .fillMaxHeight(1f)
                    .fillMaxWidth(1f)
                    .background(Color.Blue)
            ) {
            }
        }
    }
}

