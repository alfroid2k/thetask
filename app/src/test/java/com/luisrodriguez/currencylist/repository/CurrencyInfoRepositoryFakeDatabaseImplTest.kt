package com.luisrodriguez.currencylist.repository

import app.cash.turbine.test
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CurrencyInfoRepositoryFakeDatabaseImplTest {
    @Test
    fun whenLoadThenEmitANewList() = runBlocking{
        val repo = CurrencyInfoRepositoryFakeDatabaseImpl()
        repo.currenciesFlow.test {
            awaitItem()
            assertTrue(repo.currenciesFlow.value.isEmpty())
            repo.load()
            awaitItem()
            assertTrue(repo.currenciesFlow.value.isNotEmpty())
        }
    }
}