package com.example.midtermmocom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
                title = if (isEdit) "Edit Contact" else "Add Contact",
                initial = if (isEdit) vm.getContacts()[editIndex] else null,
                onSubmit = { contact ->
                    if (contact.address.split(" ").size < 5)
                        return@AddEditScreen "Address minimum 5 words"
                    if (isEdit) vm.updateContact(editIndex, contact)
                    else vm.addContact(contact)
                    navController.popBackStack()
                    null
                },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
