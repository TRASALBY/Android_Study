package com.example.retrofit_error_handling.data.di

import com.example.retrofit_error_handling.data.datasource.BookDataSource
import com.example.retrofit_error_handling.data.datasource.BookDataSourceImpl
import com.example.retrofit_error_handling.data.service.KakaoBookApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideBookDataSource(api: KakaoBookApi): BookDataSource = BookDataSourceImpl(api)

}