package com.example.midtermmocom

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.midtermmocom.Contact

class ContactViewModel : ViewModel() {
    companion object {
        val contacts = mutableStateListOf<Contact>()
    }

    fun addContact(contact: Contact) {
        contacts.add(contact)
    }

    fun updateContact(index: Int, updated: Contact) {
        contacts[index] = updated
    }

    fun getContacts() = contacts
}
