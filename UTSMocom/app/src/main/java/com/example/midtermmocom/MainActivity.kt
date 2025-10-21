package com.example.midtermmocom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contactmanager.data.ContactViewModel
import com.example.contactmanager.ui.AddEditScreen
import com.example.contactmanager.ui.ListContactScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactApp()
        }
    }
}

@Composable
fun ContactApp(vm: ContactViewModel = viewModel()) {
    val navController = rememberNavController()
    var editIndex by remember { mutableStateOf(-1) }

    NavHost(
        navController = navController,
        startDestination = Screen.ListContactScreen.route
    ) {
        composable(Screen.ListContactScreen.route) {
            ListContactScreen(
                contacts = vm.getContacts(),
                onAddClick = {
                    editIndex = -1
                    navController.navigate(Screen.AddEditScreen.route)
                },
                onLongPress = { index ->
                    editIndex = index
                    navController.navigate(Screen.AddEditScreen.route)
                }
            )
        }

        composable(Screen.AddEditScreen.route) {
            val isEdit = editIndex >= 0
            AddEditScreen(

            )
        }
    }
}
