package com.luisrodriguez.currencylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.luisrodriguez.currencylist.ui.main.MainFragment
import com.luisrodriguez.currencylist.ui.main.MainState
import com.luisrodriguez.currencylist.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()

            val composeView = findViewById<ComposeView>(R.id.main_activity_compose_view)
            composeView.setContent {
                val state = mainViewModel.stateFLow.collectAsState().value
                MainScreen(
                    onLoad = mainViewModel::loadData,
                    onSort = mainViewModel::sort,
                    isLoading = state is MainState.Loading
                )
            }
        }
    }
}

@Composable
fun MainScreen(onLoad: () -> Unit, onSort: () -> Unit, isLoading: Boolean = false) {
    Buttons(
        onLoad = { onLoad.invoke() },
        onSort = { onSort.invoke() },
        isLoading = isLoading
    )
}

@Composable
fun Buttons(onLoad: () -> Unit, onSort: () -> Unit, isLoading: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = onLoad, enabled = !isLoading) {
            Text(text = stringResource(R.string.load_button))
        }
        TextButton(onClick = onSort, enabled = !isLoading) {
            Text(text = stringResource(R.string.sort_button))
        }
        CircularProgressIndicator(
            Modifier
                .size(20.dp)
                .alpha(if (isLoading) 1f else 0f))
    }
}