package com.example.retrofit_error_handling.data.network

import com.example.retrofit_error_handling.data.error.NullBodyException
import com.example.retrofit_error_handling.data.model.ErrorResponse
import com.example.retrofit_error_handling.data.model.Result
import com.google.gson.Gson
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class SafeApiImpl @Inject constructor(
    private val gson: Gson
) : SafeApi {
    override suspend fun <ResultType, RequestType> getSafe(
        remoteFetch: suspend () -> Response<RequestType>,
        mapping: (RequestType) -> ResultType
    ): Result<ResultType> {

        lateinit var result: Result<ResultType>
        runCatching { remoteFetch() }
            .onSuccess {
                if (it.isSuccessful) {
                    val body = it.body()
                    result = if (body != null) {
                        Result.Success(mapping(body))
                    } else {
                        Result.Unexpected(NullBodyException("body가 null임"))
                    }
                } else {
                    var errorMessage = gson.fromJson(
                        it.errorBody()?.string(),
                        ErrorResponse::class.java
                    ).message

                    if (errorMessage.isNotBlank()) {
                        errorMessage = "Unknown Error"
                    }
                    result = Result.Failure(it.code(), errorMessage)
                }
            }
            .onFailure {
                result = when (it) {
                    is IOException -> Result.NetworkError(it)
                    else -> Result.Unexpected(it)
                }
            }
        return result
    }
}