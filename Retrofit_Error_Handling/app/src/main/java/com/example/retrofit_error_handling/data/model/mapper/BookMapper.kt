package com.example.retrofit_error_handling.data.model.mapper

import com.example.retrofit_error_handling.data.model.BookResponse
import com.example.retrofit_error_handling.domain.model.Book

fun BookResponse.toDomain() =
    Book(title, contents, url, price, isbn)