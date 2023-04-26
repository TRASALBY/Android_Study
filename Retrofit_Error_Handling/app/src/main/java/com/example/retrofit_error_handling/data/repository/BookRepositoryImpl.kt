package com.example.retrofit_error_handling.data.repository

import com.example.retrofit_error_handling.data.datasource.BookDataSource
import com.example.retrofit_error_handling.data.model.Result
import com.example.retrofit_error_handling.domain.model.Book
import com.example.retrofit_error_handling.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookDataSource: BookDataSource) :
    BookRepository {
    override suspend fun getBookList(query: String): Result<List<Book>> =
        bookDataSource.getBookList(query)
}