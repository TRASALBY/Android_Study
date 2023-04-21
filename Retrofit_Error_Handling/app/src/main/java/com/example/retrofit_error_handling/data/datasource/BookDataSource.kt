package com.example.retrofit_error_handling.data.datasource

import com.example.retrofit_error_handling.data.model.BookResponse
import kotlinx.coroutines.flow.Flow

interface BookDataSource {
    suspend fun getBookList(query: String): Flow<BookResponse>
}