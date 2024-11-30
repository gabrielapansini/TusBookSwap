package com.example.tusbookswap

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tusbookswap.ui.AccountScreen
import com.example.tusbookswap.ui.AddBookToSwap
import com.example.tusbookswap.ui.AddNewBookDetails
import com.example.tusbookswap.ui.AuthRegViewModel
import com.example.tusbookswap.ui.BookDescriptionScreen
import com.example.tusbookswap.ui.BooksListeningsScreen
import com.example.tusbookswap.ui.EditAccountScreen
import com.example.tusbookswap.ui.FavoriteGenresScreen
import com.example.tusbookswap.ui.HomeScreen
import com.example.tusbookswap.ui.LoginScreen
import com.example.tusbookswap.ui.MeetUpScreen
import com.example.tusbookswap.ui.Screen
import com.example.tusbookswap.ui.Screen.EditAccount
import com.example.tusbookswap.ui.SignUpScreen
import com.example.tusbookswap.ui.WishListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TUSBookSwapTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    //CustomBoxWithText()
                    //LoginScreen()
                    NavGraph()

                }
            }
        }
    }
}
//References: https://developer.android.com/guide/navigation/get-started


//function to navigate
//representing the navigation graph of the application.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(startDestination: String = Screen.SignUpScreen.route) {
    val navController = rememberNavController()
    val viewModel: AuthRegViewModel = viewModel()

    Scaffold(
        bottomBar = {
            when (navController.currentBackStackEntry?.destination?.route) {
                Screen.Home.route, Screen.FavoriteGenresScreen.route -> {
                    BottomNavigationBar(navController)
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.SignUpScreen.route) { SignUpScreen(navController, viewModel) }
            composable(Screen.LoginScreen.route) { LoginScreen(navController, viewModel) }
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.FavoriteGenresScreen.route) { FavoriteGenresScreen(navController) }
            composable(
                "book_description_screen/{bookTitle}?bookImage={bookImage}",
                arguments = listOf(
                    navArgument("bookTitle") { type = NavType.StringType },
                    navArgument("bookImage") { type = NavType.IntType; defaultValue = R.drawable.book1 }
                )
            ) { backStackEntry ->
                val bookTitle = backStackEntry.arguments?.getString("bookTitle") ?: "Unknown Book"
                val bookImage = backStackEntry.arguments?.getInt("bookImage") ?: R.drawable.book1
                BookDescriptionScreen(navController = navController, bookTitle = bookTitle, bookImage = bookImage)
            }
            composable(Screen.MeetUpScreen.route) { MeetUpScreen(navController) }
            composable(Screen.AddBookToSwap.route) {
                AddBookToSwap(navController)
            }
            composable(
                "${Screen.AddNewBookDetails.route}?imageUri={imageUri}",
                arguments = listOf(
                    navArgument("imageUri") { type = NavType.StringType; nullable = true }
                )
            ) { backStackEntry ->
                val imageUriString = backStackEntry.arguments?.getString("imageUri")
                val imageUri = imageUriString?.let { Uri.parse(it) }
                AddNewBookDetails(navController, imageUri)
            }
            composable(Screen.AccountScreen.route) { AccountScreen(navController) }
            composable(Screen.EditAccount.route) { EditAccountScreen(navController) }
            composable(Screen.WishList.route) { WishListScreen(navController) }
            composable(Screen.BooksListeningsScreen.route) { BooksListeningsScreen(navController) }


        }
    }
}




// Define custom colors
private val TransparentGold = Color(0xB3A49461) // ARGB: 70% (B3 in hex)
private val BackgroundColor = Color(0xFFF1F1F1) // F1F1F1
private val TopBarColor = Color(0xFFC6BA94) // C6BA94
private val BottomNavColor = Color(0xFF48454E) // 48454E

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar(containerColor = Color.White) {
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
            onClick = {
                selectedItem = 0
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = TransparentGold)
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
            onClick = {
                selectedItem = 1
                navController.navigate(Screen.AddBookToSwap.route) {
                    popUpTo(Screen.Home.route) { saveState = true }
                }
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = TransparentGold)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "WishList",
                    tint = if (selectedItem == 2) Color.Black else BottomNavColor
                )
            },
            label = {
                Text(
                    "WishList",
                    color = if (selectedItem == 2) Color.Black else BottomNavColor,
                    fontWeight = if (selectedItem == 2) FontWeight.Bold else FontWeight.Normal
                )
            },
            selected = selectedItem == 2,
            onClick = {
                selectedItem = 2
                navController.navigate(Screen.WishList.route) {
                    popUpTo(Screen.Home.route) { saveState = true }
                }
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = TransparentGold)
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Account",
                    tint = if (selectedItem == 2) Color.Black else BottomNavColor
                )
            },
            label = {
                Text(
                    "Account",
                    color = if (selectedItem == 2) Color.Black else BottomNavColor,
                    fontWeight = if (selectedItem == 2) FontWeight.Bold else FontWeight.Normal
                )
            },
            selected = selectedItem == 2,
            onClick = {
                selectedItem = 2
                navController.navigate(Screen.AccountScreen.route) {
                    popUpTo(Screen.Home.route) { saveState = true }
                }
            },
            colors = NavigationBarItemDefaults.colors(indicatorColor = TransparentGold)
        )
    }
}


@Composable
fun TUSBookSwapTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}