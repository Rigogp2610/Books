package com.robgar.books.di

import com.robgar.books.data.network.ApiHelper
import com.robgar.books.data.network.RetrofitBuilder
import com.robgar.books.data.repository.AllRepository
import com.robgar.books.data.repository.FilterRepository
import com.robgar.books.ui.main.MainViewModel
import com.robgar.books.ui.main.all.AllViewModel
import com.robgar.books.ui.main.filter.FilterViewModel
import com.robgar.books.ui.maintenance.MaintenanceViewModel
import com.robgar.books.usecases.GetBooksByGenreUseCase
import com.robgar.books.usecases.GetBooksUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RetrofitBuilder.getApiService() }
    single { ApiHelper(get()) }
    single { AllRepository(get()) }
    single { FilterRepository(get()) }
    single { GetBooksUseCase(get()) }
    single { GetBooksByGenreUseCase(get()) }
    viewModel { MainViewModel() }
    viewModel { AllViewModel(get()) }
    viewModel { FilterViewModel(get()) }
    viewModel { MaintenanceViewModel() }
}