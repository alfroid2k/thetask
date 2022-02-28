package com.luisrodriguez.currencylist.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisrodriguez.currencylist.repository.CurrencyInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val currencyInfoRepository: CurrencyInfoRepository) : ViewModel() {
    val stateFLow = MutableStateFlow<MainState>(MainState.Idle)

    init {
        viewModelScope.launch {
            currencyInfoRepository.currenciesFlow.map { items ->
                // Normally here the repo would wrap a response as a success or failure and we would handle accordingly
                if (items.isNotEmpty()) {
                    stateFLow.emit(MainState.Success(items = items))
                }
            }
                .collect()
        }
    }

    fun loadData() {
        viewModelScope.launch {
            stateFLow.emit(MainState.Loading)
            currencyInfoRepository.load()
        }
    }

    fun sort() {
        viewModelScope.launch {
            stateFLow.emit(MainState.Loading)
            currencyInfoRepository.sort()
        }
    }

    fun itemClicked(id: String) {

    }
}