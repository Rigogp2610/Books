package com.robgar.books.data.repository

import com.robgar.books.data.network.ApiHelper

class FilterRepository(private val apiHelper: ApiHelper) {

    suspend fun getBooksByGenre(genre: String) = apiHelper.getBooksByGenre(genre)
}