package com.wtmcodex.samplemovies.model.network

import com.google.gson.annotations.SerializedName

open class BaseDTO {
    val page: Long = 0

    @SerializedName("total_results")
    val totalResults: Long = 0

    @SerializedName("total_pages")
    val totalPages: Long = 0
}
