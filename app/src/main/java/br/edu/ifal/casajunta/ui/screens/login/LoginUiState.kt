package br.edu.ifal.casajunta.ui.screens.login

sealed interface LoginUiState {

    data object Idle : LoginUiState

    data object Loading : LoginUiState

    data object Success : LoginUiState

    data class Error(
        val message: String
    ) : LoginUiState
}