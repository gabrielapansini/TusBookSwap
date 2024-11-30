package com.example.tusbookswap.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tusbookswap.BottomNavigationBar
import com.example.tusbookswap.R

// Define custom colors
private val TopBarColor = Color(0xFFC6BA94) // Top bar color
private val BackgroundColor = Color(0xFFF1F1F1) // Background color
private val CardBackgroundColor = Color.White // Card background color
private val FavoriteIconColor = Color.Red // Favorite icon color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Wish List",
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
            // Title Section
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                tint = FavoriteIconColor,
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Your Favorite Books!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            // Grid Section
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    listOf(
                        Pair(R.drawable.book1, "Book Title 1"),
                        Pair(R.drawable.book2, "Book Title 2"),
                        Pair(R.drawable.book3, "Book Title 3"),
                        Pair(R.drawable.book4, "Book Title 4"),
                        Pair(R.drawable.book5, "Book Title 5"),
                        Pair(R.drawable.book6, "Book Title 6")
                    )
                ) { book ->
                    WishListCard(
                        bookImage = book.first,
                        bookTitle = book.second
                    )
                }
            }
        }
    }
}

@Composable
fun WishListCard(bookImage: Int, bookTitle: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(160.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = bookImage),
                        contentDescription = bookTitle,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = bookTitle,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                tint = FavoriteIconColor,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WishListScreenPreview() {
    val navController = rememberNavController()
    WishListScreen(navController)
}
