package com.example.tusbookswap.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tusbookswap.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Account",
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
                    IconButton(onClick = { /* Search action */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = com.example.tusbookswap.ui.theme.TopBarColor)
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
                .background(com.example.tusbookswap.ui.theme.BackgroundColor)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Image
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name Field
            OutlinedTextField(
                value = "Gabriela Pansini",
                onValueChange = { /* Handle Name Change */ },
                label = { Text("Name") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Name Icon")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Bio Field
            OutlinedTextField(
                value = "Bio (optional)",
                onValueChange = { /* Handle Bio Change */ },
                label = { Text("Bio") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Bio Icon")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Favorite Genres
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Navigate to Edit Favorite Genres */ }
                    .background(Color.LightGray)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Genres Icon",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = "Edit favorite genres",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditAccountScreenPreview() {
    // Create a mock NavController for preview purposes
    val navController = rememberNavController()

    // Call the EditAccountScreen composable
    EditAccountScreen(navController = navController)
}
