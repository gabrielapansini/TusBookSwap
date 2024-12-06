package com.example.tusbookswap.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

//  custom colors
private val TopBarColor = Color(0xFFC6BA94) // C6BA94
private val BackgroundColor = Color(0xFFF1F1F1) // F1F1F1
private val ButtonColor = Color(0xFFC6BA94) // Same as the top bar color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDescriptionScreen(navController: NavController, bookTitle: String, bookImage: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = bookTitle,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
            // Display Book Image
            Image(
                painter = painterResource(id = bookImage),
                contentDescription = "Book Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Book Title
            Text(
                text = bookTitle,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Swap Button
            Button(
                onClick = {
                    navController.navigate(Screen.MeetUpScreen.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text(
                    text = "Swap",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }


        }
    }
}




@Preview(showBackground = true)
@Composable
fun BookDescriptionScreenPreview() {
    val navController = rememberNavController()
    // Provide mock data for preview
    val mockBookTitle = "Sample Book Title"
    val mockBookImage = R.drawable.book1
    BookDescriptionScreen(navController, bookTitle = mockBookTitle, bookImage = mockBookImage)
}

