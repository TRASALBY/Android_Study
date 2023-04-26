package com.example.retrofit_error_handling.data.model

import com.google.gson.annotations.SerializedName

data class BookMeta(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean
)
