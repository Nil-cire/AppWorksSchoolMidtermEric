package com.eric.firebaseexample.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListArticle(
    val error: String? = null,
    val articles: List<Article?>?
): Parcelable


@Parcelize
data class Article(
    val author : Author? = null,
    val title: String? = null,
    val content: String? = null,
    val createdTime: Long? = null,
    val id: String? = null,
    val category: String? = null
): Parcelable

@Parcelize
data class Author(
    val email : String? = null,
    val id: String? = null,
    val name: String? = null
): Parcelable

//class Author {
//    var email: String? = null
//        private set
//    var id: String? = null
//        private set
//    var name: String? = null
//        private set
//
//    constructor(email: String?, id: String?, name: String?) {
//        this.email = email
//        this.id = id
//        this.name = name
//    }
//
//    //Add this
//    constructor() {}
//
//}