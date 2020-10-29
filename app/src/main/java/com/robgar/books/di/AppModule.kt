package com.robgar.books.di

import com.robgar.books.data.network.ApiHelper
import com.robgar.books.data.network.RetrofitBuilder
import com.robgar.books.data.repository.AllRepository
import com.robgar.books.data.repository.FilterRepository
import org.koin.dsl.module

val appModule = module {

    single { RetrofitBuilder.getApiService() }
    single { ApiHelper(get()) }
    single { AllRepository(get()) }
    single { FilterRepository(get()) }
}