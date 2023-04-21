package com.example.retrofit_error_handling.data.di

import com.example.retrofit_error_handling.data.datasource.BookDataSourceImpl
import com.example.retrofit_error_handling.data.repository.BookRepositoryImpl
import com.example.retrofit_error_handling.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(dataSource: BookDataSourceImpl): BookRepository =
        BookRepositoryImpl(dataSource)
}