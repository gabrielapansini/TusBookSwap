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

// Define custom colors
private val TransparentGold = Color(0xB3A49461) // ARGB: 70% (B3 in hex)
private val BackgroundColor = Color(0xFFF1F1F1) // F1F1F1
private val TopBarColor = Color(0xFFC6BA94) // C6BA94
private val BottomNavColor = Color(0xFF48454E) // 48454E

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteGenresScreen() {
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
            BottomNavigationBar()
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

@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf(0) }
    NavigationBar(
        containerColor = Color.White // Set the background color of the bottom navigation bar to white
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    tint = if (selectedItem == 0) Color.Black else BottomNavColor
                )
            },
            label = {
                Text(
                    "Home",
                    color = if (selectedItem == 0) Color.Black else BottomNavColor,
                    fontWeight = if (selectedItem == 0) FontWeight.Bold else FontWeight.Normal
                )
            },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = TransparentGold // Rounded rectangle background for selected item
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.AddCircle,
                    contentDescription = "Swap",
                    tint = if (selectedItem == 1) Color.Black else BottomNavColor
                )
            },
            label = {
                Text(
                    "Swap",
                    color = if (selectedItem == 1) Color.Black else BottomNavColor,
                    fontWeight = if (selectedItem == 1) FontWeight.Bold else FontWeight.Normal
                )
            },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = TransparentGold
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Wishlist",
                    tint = if (selectedItem == 2) Color.Black else BottomNavColor
                )
            },
            label = {
                Text(
                    "Wishlist",
                    color = if (selectedItem == 2) Color.Black else BottomNavColor,
                    fontWeight = if (selectedItem == 2) FontWeight.Bold else FontWeight.Normal
                )
            },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = TransparentGold
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Account",
                    tint = if (selectedItem == 3) Color.Black else BottomNavColor
                )
            },
            label = {
                Text(
                    "Account",
                    color = if (selectedItem == 3) Color.Black else BottomNavColor,
                    fontWeight = if (selectedItem == 3) FontWeight.Bold else FontWeight.Normal
                )
            },
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = TransparentGold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteGenresScreenPreview() {
    FavoriteGenresScreen()
}
