package br.edu.ifal.casajunta.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.edu.ifal.casajunta.data.auth.FakeAuthRepository
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LoginRoute(
    onVoltarClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                LoginViewModel(FakeAuthRepository())
            }
        }
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState == LoginUiState.Success) {
            onLoginSuccess()
        }
    }

    LoginScreen(
        uiState = uiState,
        onVoltarClick = onVoltarClick,
        onEntrarClick = { email, senha ->
            viewModel.login(email, senha)
        }
    )
}