package com.example.retrofit_error_handling.data.service

import com.example.retrofit_error_handling.data.model.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoBookApi {
    @GET("/b3/search/book")
    fun searchBook(
        @Query("query")
        query: String
    ):Response<BookResponse>
}