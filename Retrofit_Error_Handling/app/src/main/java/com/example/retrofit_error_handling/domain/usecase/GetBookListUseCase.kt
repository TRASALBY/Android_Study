package com.example.retrofit_error_handling.domain.usecase

import com.example.retrofit_error_handling.domain.repository.BookRepository
import javax.inject.Inject

class GetBookListUseCase @Inject constructor(private val bookRepository: BookRepository){
    suspend operator fun invoke(query: String) = bookRepository.getBookList(query)
}