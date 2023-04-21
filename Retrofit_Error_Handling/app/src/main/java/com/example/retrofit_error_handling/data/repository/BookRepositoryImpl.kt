package com.example.retrofit_error_handling.data.repository

import com.example.retrofit_error_handling.data.datasource.BookDataSource
import com.example.retrofit_error_handling.data.model.mapper.toDomain
import com.example.retrofit_error_handling.domain.repository.BookRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookDataSource: BookDataSource) :
    BookRepository {
    override suspend fun getBookList(query: String) =
        bookDataSource.getBookList(query).map { bookDocuments ->
            bookDocuments.map { it.toDomain() }
        }
}