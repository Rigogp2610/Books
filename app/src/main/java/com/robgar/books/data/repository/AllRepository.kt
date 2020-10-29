package com.robgar.books.data.repository

import com.robgar.books.data.network.ApiHelper

class AllRepository(private val apiHelper: ApiHelper) {

    suspend fun getBooks() = apiHelper.getBooks()
}