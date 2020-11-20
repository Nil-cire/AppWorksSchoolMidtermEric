package com.eric.firebaseexample.publisher

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eric.firebaseexample.home.HomeViewModel
import com.google.firebase.firestore.FirebaseFirestore

class PublisherViewModelFactory (
    private val database: FirebaseFirestore,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PublisherViewModel::class.java)) {
            return PublisherViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}