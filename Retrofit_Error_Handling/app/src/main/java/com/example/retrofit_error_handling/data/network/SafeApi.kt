package com.example.retrofit_error_handling.data.network

import com.example.retrofit_error_handling.data.model.Result
import retrofit2.Response

interface SafeApi {
    suspend fun <ResultType, RequestType> getSafe(
        remoteFetch: suspend () -> Response<RequestType>,
        mapping: (RequestType) -> (ResultType)
    ): Result<ResultType>

}