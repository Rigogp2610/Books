package com.robgar.books.usecases

import com.robgar.books.data.repository.AllRepository

class BooksUseCase(private val allRepository: AllRepository) {
    suspend operator fun invoke() = allRepository.getBooks()
}