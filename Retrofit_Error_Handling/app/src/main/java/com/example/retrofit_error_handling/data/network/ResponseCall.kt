package com.example.retrofit_error_handling.data.network

import com.example.retrofit_error_handling.data.error.NullBodyException
import com.example.retrofit_error_handling.data.model.ErrorResponse
import com.example.retrofit_error_handling.data.model.Result
import com.google.gson.Gson
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ResponseCall<T>(
    private val callDelegate: Call<T>
) : Call<Result<T>> {

    val gson = Gson()
    override fun clone(): Call<Result<T>> = ResponseCall(callDelegate.clone())

    override fun execute(): Response<Result<T>> =
        throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted() = callDelegate.isExecuted
    override fun cancel() = callDelegate.cancel()
    override fun isCanceled() = callDelegate.isCanceled
    override fun request(): Request = callDelegate.request()
    override fun timeout(): Timeout = callDelegate.timeout()
    override fun enqueue(callback: Callback<Result<T>>) =
        callDelegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@ResponseCall,
                            Response.success(Result.Success(body))
                        )
                    } else {
                        callback.onResponse(
                            this@ResponseCall,
                            Response.success(Result.Unexpected(NullBodyException("body가 null임")))
                        )
                    }
                } else {
                    val errorMessage = runCatching {
                        gson.fromJson(
                            response.errorBody()?.string(),
                            ErrorResponse::class.java
                        ).message
                    }.getOrElse { "Unknown Error" }
                    callback.onResponse(
                        this@ResponseCall,
                        Response.success(Result.Failure(response.code(), errorMessage))
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result: Result<T> = when (t) {
                    is IOException -> Result.NetworkError(t)
                    else -> Result.Unexpected(t)
                }
                callback.onResponse(this@ResponseCall, Response.success(result))
            }

        })
}