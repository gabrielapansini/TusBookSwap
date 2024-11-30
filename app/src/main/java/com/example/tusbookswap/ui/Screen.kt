package com.example.tusbookswap.ui


object Screen {
    val SignUpScreen = ScreenItem("signup_screen")
    val LoginScreen = ScreenItem("login_screen")
    val Home = ScreenItem("home")
    val FavoriteGenresScreen = ScreenItem ("favorite_genres_screen")
    val BookDescriptionScreen = ScreenItem("book_description_screen/{bookTitle}") // Dynamic route
}

data class ScreenItem(val route: String)
