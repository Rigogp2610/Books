package com.robgar.books.data.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getBooks() = apiService.getBooks()

    suspend fun getBooksByGenre(genre: String) = apiService.getBooksByGenre(genre)

    suspend fun getBook(messageId: Int) = apiService.getBook(messageId)
}