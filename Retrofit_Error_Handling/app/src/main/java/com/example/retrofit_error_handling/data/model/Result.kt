package com.example.retrofit_error_handling.data.model

sealed class Result<out T>{
    data class Success<T>(val data: T): Result<T>()
    data class Failure(val code: Int, val message: String?) : Result<Nothing>()
    data class NetworkError(val throwable: Throwable) : Result<Nothing>()
    data class Unexpected(val throwable: Throwable) : Result<Nothing>()
}
