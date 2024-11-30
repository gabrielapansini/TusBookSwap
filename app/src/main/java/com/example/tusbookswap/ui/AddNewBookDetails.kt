package com.example.tusbookswap.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.tusbookswap.BottomNavigationBar

// Define custom colors
private val TopBarColor = Color(0xFFC6BA94)
private val BackgroundColor = Color(0xFFF1F1F1)
private val ButtonColor = Color(0xFFC6BA94)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewBookDetails(navController: NavController, imageUri: Uri?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add new Book",
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
            Text(
                text = "Details",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Display the image passed from AddBookToSwap
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                imageUri?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = "Captured Image",
                        modifier = Modifier.fillMaxSize()
                    )
                } ?: Text("No Image", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input fields for book details
            TextField(
                value = "",
                onValueChange = { /* Handle Title input */ },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
            TextField(
                value = "",
                onValueChange = { /* Handle Author input */ },
                label = { Text("Author") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )
            TextField(
                value = "",
                onValueChange = { /* Handle Category input */ },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
            )

            Button(
                onClick = { navController.navigate("listening") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
            ) {
                Text(
                    text = "List",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewBookDetailsPreview() {
    val navController = rememberNavController()
    // Use a placeholder URI or null for preview purposes
    AddNewBookDetails(navController, imageUri = null)
}
