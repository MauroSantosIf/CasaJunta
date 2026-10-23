package br.edu.ifal.casajunta.data.auth

import kotlinx.coroutines.delay

class FakeAuthRepository : AuthRepository {

    override suspend fun login(
        email: String,
        senha: String
    ): Boolean {
        delay(800)

        return email.trim().equals(
            "test@casajunta.com",
            ignoreCase = true
        ) && senha == "123456"
    }
}