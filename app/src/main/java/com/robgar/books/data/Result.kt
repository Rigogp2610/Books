package com.robgar.books.data

sealed class Result<out T: Any> {

    data class Success<out T: Any>(val data: T) : Result<T>()
    data class Error<out T: Any>(val message: String) : Result<T>()
    data class Loading<out T: Any>(val data: T?) : Result<T>()
}