package com.example.retrofit_error_handling.domain.repository

import com.example.retrofit_error_handling.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun getBookList(query: String) : Flow<List<Book>>
}