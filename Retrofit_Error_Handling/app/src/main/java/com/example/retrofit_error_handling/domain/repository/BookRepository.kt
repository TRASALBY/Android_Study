package com.example.retrofit_error_handling.domain.repository

import com.example.retrofit_error_handling.data.model.Result
import com.example.retrofit_error_handling.domain.model.Book
interface BookRepository {
    suspend fun getBookList(query: String) : Result<List<Book>>
}