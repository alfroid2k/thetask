package com.luisrodriguez.currencylist.repository

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class CurrencyInfoRepositoryFakeDatabaseImpl : CurrencyInfoRepository {
    private val gson = Gson()
    override val currenciesFlow = MutableStateFlow(emptyList<CurrencyInfo>())

    override suspend fun load() {
        withContext(Dispatchers.IO) {
            currenciesFlow.emit(emptyList())
            delay(2000)
            val list = gson.fromJson(
                "[{\n" +
                        "\t\t\"id\": \"BTC\",\n" +
                        "\t\t\"name\": \"Bitcoin\",\n" +
                        "\t\t\"symbol\": \"BTC\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"ETH\",\n" +
                        "\t\t\"name\": \"Ethereum\",\n" +
                        "\t\t\"symbol\": \"ETH\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"XRP\",\n" +
                        "\t\t\"name\": \"XRP\",\n" +
                        "\t\t\"symbol\": \"XRP\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"BCH\",\n" +
                        "\t\t\"name\": \"Bitcoin Cash\",\n" +
                        "\t\t\"symbol\": \"BCH\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"LTC\",\n" +
                        "\t\t\"name\": \"Litecoin\",\n" +
                        "\t\t\"symbol\": \"LTC\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"EOS\",\n" +
                        "\t\t\"name\": \"EOS\",\n" +
                        "\t\t\"symbol\": \"EOS\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"BNB\",\n" +
                        "\t\t\"name\": \"Binance Coin\",\n" +
                        "\t\t\"symbol\": \"BNB\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"LINK\",\n" +
                        "\t\t\"name\": \"Chainlink\",\n" +
                        "\t\t\"symbol\": \"LINK\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"NEO\",\n" +
                        "\t\t\"name\": \"NEO\",\n" +
                        "\t\t\"symbol\": \"NEO\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"ETC\",\n" +
                        "\t\t\"name\": \"Ethereum Classic\",\n" +
                        "\t\t\"symbol\": \"ETC\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"ONT\",\n" +
                        "\t\t\"name\": \"Ontology\",\n" +
                        "\t\t\"symbol\": \"ONT\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"CRO\",\n" +
                        "\t\t\"name\": \"Crypto.com Chain\",\n" +
                        "\t\t\"symbol\": \"CRO\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"CUC\",\n" +
                        "\t\t\"name\": \"Cucumber\",\n" +
                        "\t\t\"symbol\": \"CUC\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"id\": \"USDC\",\n" +
                        "\t\t\"name\": \"USD Coin\",\n" +
                        "\t\t\"symbol\": \"USDC\"\n" +
                        "\t}\n" +
                        "]", Array<CurrencyInfo>::class.java
            ).toList()
            currenciesFlow.emit(list.sortedBy { it.symbol })
        }
    }

    override suspend fun sort() {
        withContext(Dispatchers.IO) {
            delay(2000)
            currenciesFlow.emit(currenciesFlow.value.asReversed())
        }
    }
}