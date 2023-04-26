package com.example.retrofit_error_handling.data.datasource

import com.example.retrofit_error_handling.data.model.Result
import com.example.retrofit_error_handling.data.model.mapper.toDomain
import com.example.retrofit_error_handling.data.network.SafeApi
import com.example.retrofit_error_handling.data.service.KakaoBookApi
import com.example.retrofit_error_handling.domain.model.Book
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(
    private val bookApi: KakaoBookApi,
    private val safeApi: SafeApi
    ) : BookDataSource {
    override suspend fun getBookList(query: String): Result<List<Book>> = safeApi.getSafe(
        remoteFetch = {
            bookApi.searchBook(query)
        },
        mapping = { response ->
            response.bookDocuments.map {
                it.toDomain()
            }
        }
    )
}