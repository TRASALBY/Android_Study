package com.example.retrofit_error_handling.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_error_handling.data.model.Result
import com.example.retrofit_error_handling.domain.model.mapper.toPresentation
import com.example.retrofit_error_handling.domain.usecase.GetBookListUseCase
import com.example.retrofit_error_handling.presentation.model.BookUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase
) : ViewModel() {

    private val _bookList: MutableStateFlow<List<BookUiModel>> = MutableStateFlow(emptyList())
    val bookList = _bookList.asStateFlow()

    fun getBookList(query: String) {
        viewModelScope.launch {
            when (val response = getBookListUseCase(query)){
                is Result.Success -> _bookList.update { response.data.map { it.toPresentation() } }
                is Result.Failure -> Log.e(getBookListUseCase.tag, response.message ?: "")
                is Result.NetworkError -> Log.e(getBookListUseCase.tag, "네트워크 에러")
                is Result.Unexpected -> Log.e(getBookListUseCase.tag, "알수없는 에러")
            }
        }
    }
}