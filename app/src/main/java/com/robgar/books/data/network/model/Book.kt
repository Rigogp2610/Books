package com.robgar.books.data.network.model

import com.google.gson.annotations.SerializedName

data class Book (
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("isbn")
    val isbn: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("genre")
    val genre: String
)