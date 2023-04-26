package com.example.retrofit_error_handling.data.di

import com.example.retrofit_error_handling.BuildConfig
import com.example.retrofit_error_handling.data.network.ResultCallAdapter
import com.example.retrofit_error_handling.data.service.KakaoBookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        headerInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(headerInterceptor)
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor() = object : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
            request.addHeader("Authorization","KakaoAK ${BuildConfig.API_KEY}")
            return chain.proceed(request.build())
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapter.Factory())
        .build()

    @Singleton
    @Provides
    fun provideKakaoBookApi(
        retrofit: Retrofit
    ): KakaoBookApi = retrofit.create(KakaoBookApi::class.java)

}