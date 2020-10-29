package com.robgar.books.ui.main.filter

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robgar.books.data.Result
import com.robgar.books.data.network.model.Book
import com.robgar.books.usecases.GetBooksByGenreUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FilterViewModel(private val getBooksByGenreUseCase: GetBooksByGenreUseCase) : ViewModel() {

    companion object {
        const val FILTER = "filter"

        fun createArguments(genre: String) = bundleOf(
                FILTER to genre
        )
    }

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val books = MutableLiveData<Result<List<Book>>>()

    fun getBooks(arguments: Bundle?) {
        books.postValue(Result.Loading(null))
        coroutineScope.launch {
            try {
                val bookList = getBooksByGenreUseCase(arguments?.getString(FILTER) ?: "")
                books.postValue(Result.Success(bookList))
            } catch (e: Exception) {
                books.postValue(Result.Error(e.message ?: "Error Occurred!"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.coroutineContext.cancel()
    }
}