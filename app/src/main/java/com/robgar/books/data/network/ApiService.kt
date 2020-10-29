package com.robgar.books.data.network

import com.robgar.books.data.network.model.Book
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("getMessages")
    suspend fun getBooks(): List<Book>

    @GET("getMessages")
    suspend fun getBooksByGenre(@Query("genre") genre: String): List<Book>

    @GET("getMessage")
    suspend fun getBook(@Query("messageId") messageId: Int): Book
}