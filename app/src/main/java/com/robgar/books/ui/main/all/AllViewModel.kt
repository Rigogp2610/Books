package com.robgar.books.ui.main.all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robgar.books.data.network.model.Book
import com.robgar.books.usecases.GetBooksUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import com.robgar.books.data.Result
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AllViewModel(private val getBooksUseCase: GetBooksUseCase) : ViewModel() {

    val books = MutableLiveData<Result<List<Book>>>()
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun getBooks() {
        books.postValue(Result.Loading(null))
        coroutineScope.launch {
            try {
                val bookList = getBooksUseCase()
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