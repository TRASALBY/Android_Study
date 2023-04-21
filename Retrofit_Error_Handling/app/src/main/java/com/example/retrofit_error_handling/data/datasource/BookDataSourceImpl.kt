package com.example.retrofit_error_handling.data.datasource

import com.example.retrofit_error_handling.data.model.BookResponse
import com.example.retrofit_error_handling.data.service.KakaoBookApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(private val bookApi: KakaoBookApi): BookDataSource {
    override suspend fun getBookList(query: String): Flow<BookResponse> = flow {
        emit(bookApi.searchBook(query))
    }
}