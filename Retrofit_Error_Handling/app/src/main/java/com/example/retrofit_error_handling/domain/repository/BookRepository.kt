package com.example.retrofit_error_handling.domain.repository

import com.example.retrofit_error_handling.presentation.model.BookUiModel
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBookList(query: String) : Flow<BookUiModel>
}