package com.example.micrecordmultipart

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("temp")
    fun uploadAudio(
        @Part audioFile: MultipartBody.Part
    ): Call<String>
}