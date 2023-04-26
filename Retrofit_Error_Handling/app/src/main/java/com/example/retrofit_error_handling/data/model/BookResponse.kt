package com.example.retrofit_error_handling.data.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    val meta: BookMeta,
    @SerializedName("documents")
    val bookDocuments: List<BookDocument>
)
