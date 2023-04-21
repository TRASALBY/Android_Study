package com.example.retrofit_error_handling.data.model

data class BookResponse(
    val title: String,
    val contents: String,
    val url: String,
    val price: Int,
    val isbn: String
)
