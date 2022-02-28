package com.luisrodriguez.currencylist

import com.luisrodriguez.currencylist.repository.CurrencyInfoRepository
import com.luisrodriguez.currencylist.repository.CurrencyInfoRepositoryFakeDatabaseImpl
import com.luisrodriguez.currencylist.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CurrencyInfoRepository> {
        CurrencyInfoRepositoryFakeDatabaseImpl()
    }

    viewModel {
        MainViewModel(get())
    }
}