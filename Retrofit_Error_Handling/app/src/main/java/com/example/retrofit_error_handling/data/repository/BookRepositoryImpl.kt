package com.example.retrofit_error_handling.data.repository

import com.example.retrofit_error_handling.data.datasource.BookDataSource
import com.example.retrofit_error_handling.data.model.Result
import com.example.retrofit_error_handling.data.model.mapper.toDomain
import com.example.retrofit_error_handling.domain.model.Book
import com.example.retrofit_error_handling.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookDataSource: BookDataSource) :
    BookRepository {
    override suspend fun getBookList(query: String) : Result<List<Book>> {
        return when (val response = bookDataSource.getBookList(query)) {
            is Result.Success -> Result.Success(response.data.bookDocuments.map { it.toDomain() })
            is Result.Failure -> Result.Failure(response.code, response.message)
            is Result.NetworkError -> Result.NetworkError(response.throwable)
            is Result.Unexpected -> Result.Unexpected(response.throwable)
        }
    }
}