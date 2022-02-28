package com.luisrodriguez.currencylist.repository

import kotlinx.coroutines.flow.MutableStateFlow

interface CurrencyInfoRepository {

    val currenciesFlow : MutableStateFlow<List<CurrencyInfo>>

    suspend fun load()
    suspend fun sort()
}