package br.edu.ifal.casajunta.data.auth

interface AuthRepository {
    suspend fun login(
        email: String,
        senha: String
    ): Boolean
}