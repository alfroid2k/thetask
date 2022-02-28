package com.luisrodriguez.currencylist.ui.main

import com.luisrodriguez.currencylist.repository.CurrencyInfo

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Success(val items: List<CurrencyInfo>): MainState()
}