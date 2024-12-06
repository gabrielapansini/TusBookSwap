package com.example.tusbookswap.ui

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tusbookswap.BottomNavigationBar

//  custom colors
private val TopBarColor = Color(0xFFC6BA94) // C6BA94
private val BackgroundColor = Color(0xFFF1F1F1) // F1F1F1
private val ButtonColor = Color(0xFFC6BA94) // Button color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetUpScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Meet Up",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /* Handle search action */ },
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(TopBarColor)
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = TopBarColor)
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundColor)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Select the Date and the Time For Meet Up:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF5A423C))
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Interactive Map
            InteractiveMapView()

            Spacer(modifier = Modifier.height(16.dp))

            // Calendar and Time Selection
            Text(
                text = "NOVEMBER",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Add a CalendarView here if using a library

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Button
            Button(
                onClick = { /* Handle confirm action */ },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text(
                    text = "Confirm",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun InteractiveMapView() {
    val context = LocalContext.current
    AndroidView(
        factory = {
            WebView(context).apply {
                settings.javaScriptEnabled = true // Enable JavaScript for interactive features
                settings.domStorageEnabled = true // Enable DOM storage
                webViewClient = WebViewClient() // Keep navigation inside the WebView
                loadUrl("https://app.mappedin.com/map/65e1f95a789fb8fd13e8c5cc") // URL of the interactive map
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
    )
}

@Preview(showBackground = true)
@Composable
fun MeetUpScreenPreview() {
    val navController = rememberNavController()
    MeetUpScreen(navController)
}
