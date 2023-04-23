package com.example.retrofit_error_handling.data.datasource

import com.example.retrofit_error_handling.data.model.BookResponse
import com.example.retrofit_error_handling.data.model.Result

interface BookDataSource {
    suspend fun getBookList(query: String): Result<BookResponse>
}