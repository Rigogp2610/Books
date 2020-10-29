package com.robgar.books.usecases

import com.robgar.books.data.repository.FilterRepository

class GetBooksByGenreUseCase(private val filterRepository: FilterRepository) {
    suspend operator fun invoke(genre: String) = filterRepository.getBooksByGenre(genre)
}