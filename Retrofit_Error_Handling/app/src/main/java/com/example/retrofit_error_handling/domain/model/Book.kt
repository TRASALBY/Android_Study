package com.example.retrofit_error_handling.domain.model

data class Book(
    val title: String,
    val contents: String,
    val url: String,
    val price: Int,
    val isbn: String,
    val thumbnail: String
)