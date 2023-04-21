package com.example.retrofit_error_handling.presentation

import androidx.lifecycle.ViewModel
import com.example.retrofit_error_handling.presentation.model.BookUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _bookList: MutableStateFlow<List<BookUiModel>> = MutableStateFlow(emptyList())
    val bookList = _bookList.asStateFlow()

    fun getBookList() {
        // 도서 요청
    }
}