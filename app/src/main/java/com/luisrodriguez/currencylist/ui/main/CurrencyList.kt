package com.luisrodriguez.currencylist.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luisrodriguez.currencylist.repository.CurrencyInfo

@Composable
fun CurrencyList(
    modifier: Modifier,
    currencyList: List<CurrencyInfo>,
    onItemClick: (id: String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(currencyList) { currencyInfo ->
            CurrencyItem(
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 50.dp),
                currencyInfo,
                onItemClick = onItemClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrencyItem(modifier: Modifier, item: CurrencyInfo, onItemClick: (id: String) -> Unit) {
    Card(
        modifier = modifier,
        onClick = { onItemClick.invoke(item.id) },
        elevation = 8.dp,
    ) {
        Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = item.name)
            Spacer(Modifier.weight(1f))
            Box(
                Modifier
                    .background(color = Color.Gray, shape = CircleShape)
                    .padding(4.dp)
            ) {
                Text(text = item.symbol, color = Color.White)
            }
            Icon(Icons.Filled.KeyboardArrowRight, "arrow")
        }
    }
}

@Preview(name = "List Populated")
@Composable
private fun ListPopulated_Preview() {
    CurrencyList(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
        currencyList = listOf(
            CurrencyInfo(id = "123", name = "Bitcoin", symbol = "BTC"),
            CurrencyInfo(id = "123", name = "Crypto.com", symbol = "MCO"),
            CurrencyInfo(id = "123", name = "Crypto.com Chain", symbol = "CRO")
        ),
        onItemClick = {}
    )
}