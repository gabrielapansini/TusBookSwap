package com.example.tusbookswap.ui


object Screen {
    val SignUpScreen = ScreenItem("signup_screen")
    val LoginScreen = ScreenItem("login_screen")
    val Home = ScreenItem("home")
    val FavoriteGenresScreen = ScreenItem ("favorite_genres_screen")
    val BookDescriptionScreen = ScreenItem("book_description_screen/{bookTitle}") // Dynamic route
    val MeetUpScreen = ScreenItem("meetUpScreen")
    val AddBookToSwap = ScreenItem ("add_book_to_swap")
    val AddNewBookDetails = ScreenItem ("add_new_book_details")
    val AccountScreen = ScreenItem ("account_screen")
    val EditAccount = ScreenItem ("edit_account")
    val WishList = ScreenItem ("wishlist")
    val BooksListeningsScreen = ScreenItem ("listening")


}

data class ScreenItem(val route: String)
