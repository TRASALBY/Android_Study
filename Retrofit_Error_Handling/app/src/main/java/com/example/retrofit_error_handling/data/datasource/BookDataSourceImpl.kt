package com.example.retrofit_error_handling.data.datasource

import com.example.retrofit_error_handling.data.model.BookDocument
import com.example.retrofit_error_handling.data.service.KakaoBookApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(private val bookApi: KakaoBookApi): BookDataSource {
    override suspend fun getBookList(query: String): Flow<List<BookDocument>> = flow {
        val a = bookApi.searchBook(query)
        emit(a.bookDocuments)
    }
}