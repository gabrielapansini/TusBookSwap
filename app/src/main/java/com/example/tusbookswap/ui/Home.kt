package com.example.tusbookswap.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import com.example.tusbookswap.BottomNavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.tusbookswap.R

//  custom colors
private val TopBarColor = Color(0xFFC6BA94) // C6BA94
private val BackgroundColor = Color(0xFFF1F1F1) // F1F1F1
private val CardBackgroundColor = Color.White
private val FavoriteIconColor = Color.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val favoriteBooks = remember { mutableStateOf(mutableMapOf<String, Boolean>()) } // Tracks favorite status by book title

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Home",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
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
            Text(
                text = "FRESH FROM TODAY!",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    listOf(
                        Pair(R.drawable.book1, "Inner Child"),
                        Pair(R.drawable.book2, "Walk into the Shadow"),
                        Pair(R.drawable.book3, "The First Time Traveller"),
                        Pair(R.drawable.book4, "Art"),
                        Pair(R.drawable.book5, "My Prayer Journal"),
                        Pair(R.drawable.book6, "Beaux-Arts Architecture")
                    )
                ) { book ->
                    BookCard(
                        navController = navController,
                        bookImage = book.first,
                        bookTitle = book.second,
                        isFavorite = favoriteBooks.value[book.second] ?: false,
                        onFavoriteClick = { isFavorite ->
                            favoriteBooks.value = favoriteBooks.value.toMutableMap().apply {
                                this[book.second] = isFavorite
                            }
                            if (isFavorite) {
                                navController.navigate("wish_list_screen")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BookCard(
    navController: NavController,
    bookImage: Int,
    bookTitle: String,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(160.dp)
            .clickable {
                // Navigate to BookDescriptionScreen with bookImage and bookTitle
                navController.navigate(
                    "book_description_screen/$bookTitle?bookImage=$bookImage"
                )
            },
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
            IconButton(
                onClick = { onFavoriteClick(!isFavorite) },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Icon",
                    tint = if (isFavorite) FavoriteIconColor else Color.Gray
                )
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}
