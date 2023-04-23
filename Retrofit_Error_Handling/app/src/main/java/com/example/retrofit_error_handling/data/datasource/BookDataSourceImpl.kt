package com.example.retrofit_error_handling.data.datasource

import com.example.retrofit_error_handling.data.model.BookResponse
import com.example.retrofit_error_handling.data.model.Result
import com.example.retrofit_error_handling.data.service.KakaoBookApi
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(private val bookApi: KakaoBookApi) : BookDataSource {
    override suspend fun getBookList(query: String): Result<BookResponse> = bookApi.searchBook(query)
}