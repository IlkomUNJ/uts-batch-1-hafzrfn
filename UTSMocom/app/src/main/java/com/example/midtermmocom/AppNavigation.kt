package com.example.midtermmocom
sealed class Screen(val route: String){
    object ListContactScreen : Screen("listcontact")
    object AddEditScreen: Screen("addedit")
}