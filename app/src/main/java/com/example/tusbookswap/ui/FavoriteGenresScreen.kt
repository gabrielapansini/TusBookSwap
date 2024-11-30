package com.example.tusbookswap.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import com.example.tusbookswap.BottomNavigationBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// Define custom colors
private val TransparentGold = Color(0xB3A49461) // ARGB: 70% (B3 in hex)
private val BackgroundColor = Color(0xFFF1F1F1) // F1F1F1
private val TopBarColor = Color(0xFFC6BA94) // C6BA94
private val BottomNavColor = Color(0xFF48454E) // 48454E

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteGenresScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Favorite Genres",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /* Handle search action */ },
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(TransparentGold)
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = BottomNavColor
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
            Text(
                text = "What do you like to read?",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    listOf(
                        "Classics", "Education", "Fiction", "Romance", "Art",
                        "Biography", "Business", "Humor", "Comics", "History",
                        "Poetry", "Children's", "Sci-Fi", "Non Fiction",
                        "True Crime", "Cooking", "Suspense"
                    )
                ) { genre ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { /* Handle selection */ },
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = genre)
                        }
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun FavoriteGenresScreenPreview() {
    val navController = rememberNavController()
    FavoriteGenresScreen(navController)
}
