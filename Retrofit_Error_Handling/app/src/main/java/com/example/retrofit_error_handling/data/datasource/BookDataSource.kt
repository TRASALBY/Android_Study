package com.example.retrofit_error_handling.data.datasource

import com.example.retrofit_error_handling.data.model.Result
import com.example.retrofit_error_handling.domain.model.Book

interface BookDataSource {
    suspend fun getBookList(query: String): Result<List<Book>>
}