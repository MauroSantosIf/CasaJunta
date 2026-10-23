package br.edu.ifal.casajunta.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifal.casajunta.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(
        LoginUiState.Idle
    )

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun login(email: String, senha: String) {
        if (_uiState.value == LoginUiState.Loading) return

        if (email.isBlank() || senha.isBlank()) {
            _uiState.value = LoginUiState.Error(
                "Preencha o e-mail e a senha."
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            try {
                val autenticado = repository.login(
                    email = email.trim(),
                    senha = senha
                )

                _uiState.value = if (autenticado) {
                    LoginUiState.Success
                } else {
                    LoginUiState.Error(
                        "E-mail ou senha incorretos."
                    )
                }
            } catch (e: Exception) {
                if (e is kotlinx.coroutines.CancellationException) {
                    throw e
                }

                _uiState.value = LoginUiState.Error(
                    "Não foi possível realizar o login."
                )
            }
        }
    }
}