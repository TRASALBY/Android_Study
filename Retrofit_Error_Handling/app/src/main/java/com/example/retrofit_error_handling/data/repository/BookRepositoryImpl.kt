package com.example.retrofit_error_handling.data.repository

import com.example.retrofit_error_handling.data.datasource.BookDataSourceImpl
import com.example.retrofit_error_handling.domain.repository.BookRepository
import com.example.retrofit_error_handling.presentation.model.BookUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(dataSource: BookDataSourceImpl) : BookRepository{
    override suspend fun getBookList(query: String): Flow<BookUiModel> {
        TODO("Not yet implemented")
    }
}