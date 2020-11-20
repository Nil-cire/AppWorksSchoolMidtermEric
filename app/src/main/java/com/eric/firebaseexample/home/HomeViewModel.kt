package com.eric.firebaseexample.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eric.firebaseexample.data.Article
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.*

class HomeViewModel(database: FirebaseFirestore, app: Application): AndroidViewModel(app) {

    val db = database

    // testing
//    val x =
    //

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    val getData = MutableLiveData<Int>()

    init {
        getArticle()
    }

    fun getArticle() {
            db.collection("articles")
                .orderBy("createdTime", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener {
                    it?.let {
                        _articles.value = it.toObjects(Article::class.java)
                        getData.value = 1
                    }
                }

//        _articles.value = data

    }

    fun addpost() {
        val article = hashMapOf<String, Any>(
            "author" to hashMapOf(
                "email" to ".com",
                "id" to "eric1141",
                "name" to "eric"

            ),
            "title" to "考場安靜",
            "content" to "窒息了",
            "id" to "ox",
            "createdTime" to Calendar.getInstance().timeInMillis,
            "category" to "SchoolLife"
        )

        db.collection("articles").add(article)
    }


}