package com.example.retrofit_error_handling.data.model.mapper

import com.example.retrofit_error_handling.data.model.BookDocument
import com.example.retrofit_error_handling.domain.model.Book

fun BookDocument.toDomain() =
    Book(title, contents, url, price, isbn)