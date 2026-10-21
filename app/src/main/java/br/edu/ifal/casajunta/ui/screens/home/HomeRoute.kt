package br.edu.ifal.casajunta.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.edu.ifal.casajunta.data.home.FakeHomeRepository

@Composable
fun HomeRoute(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                HomeViewModel(FakeHomeRepository())
            }
        },
    ),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        isDarkTheme = isDarkTheme,
        onToggleTheme = onToggleTheme,
        onNotificationsClick = {},
        onHouseProfileClick = {},
        onAddMemberClick = {},
        onBalanceClick = {},
        onTasksClick = {},
        onBillsClick = {},
        onPantryClick = {},
        modifier = modifier,
    )
}
