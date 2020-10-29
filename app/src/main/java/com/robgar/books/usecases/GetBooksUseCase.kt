package com.robgar.books.usecases

import com.robgar.books.data.repository.AllRepository

class GetBooksUseCase(private val allRepository: AllRepository) {
    suspend operator fun invoke() = allRepository.getBooks()
}