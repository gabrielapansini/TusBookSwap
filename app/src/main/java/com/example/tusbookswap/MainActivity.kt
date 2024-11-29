package com.example.tusbookswap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tusbookswap.ui.AuthRegViewModel
import com.example.tusbookswap.ui.LoginScreen
import com.example.tusbookswap.ui.Screen
import com.example.tusbookswap.ui.SignUpScreen

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
//startDestination The starting destination of the navigation graph.
@Composable
fun NavGraph(startDestination: String = Screen.SignUpScreen.route) {
    val navController = rememberNavController()
    val viewModel: AuthRegViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.SignUpScreen.route) { SignUpScreen(navController, viewModel) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController, viewModel) }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TUSBookSwapTheme {
        // CustomBoxWithText()
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