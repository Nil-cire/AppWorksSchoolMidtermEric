package com.eric.firebaseexample.publisher

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class PublisherViewModel(database: FirebaseFirestore, app: Application): AndroidViewModel(app) {

    val donepost = MutableLiveData<Int>()

    val db = database

    fun postArticle(email:String?=null, id:String?=null, name:String?=null, title: String, category: String, content: String) {

        val article = hashMapOf<String, Any>(
            "author" to hashMapOf(
                "email" to email,
                "id" to id,
                "name" to name
            ),
            "title" to title,
            "content" to content,
            "createdTime" to Calendar.getInstance().timeInMillis,
            "id" to "eric",
            "category" to category
        )

        if (email != null && id != null && name != null) {
            db.collection("articles").add(article)
            donePost()
            resetdonePost()
        }
    }

    fun donePost() {
        donepost.value = 1
    }

    fun resetdonePost() {
        donepost.value = 0
    }
}