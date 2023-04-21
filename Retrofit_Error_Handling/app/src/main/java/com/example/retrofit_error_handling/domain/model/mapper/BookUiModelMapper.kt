package com.example.retrofit_error_handling.domain.model.mapper

import com.example.retrofit_error_handling.domain.model.Book
import com.example.retrofit_error_handling.presentation.model.BookUiModel

fun Book.toPresentation() =
    BookUiModel(title, contents, url, price, isbn.toInt())