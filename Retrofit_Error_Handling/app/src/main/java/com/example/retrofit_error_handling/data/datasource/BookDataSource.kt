package com.example.retrofit_error_handling.data.datasource

import com.example.retrofit_error_handling.data.model.BookDocument
import kotlinx.coroutines.flow.Flow

interface BookDataSource {
    suspend fun getBookList(query: String): Flow<List<BookDocument>>
}