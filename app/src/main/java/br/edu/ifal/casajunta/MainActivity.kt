
package br.edu.ifal.casajunta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import br.edu.ifal.casajunta.ui.screens.login.LoginScreen
import br.edu.ifal.casajunta.ui.screens.myHouses.MyHousesScreen
import br.edu.ifal.casajunta.ui.screens.onboarding.OnboardingScreen
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            CasaJuntaTheme {

                var telaAtual by rememberSaveable {
                    mutableStateOf("ONBOARDING")
                }

                when (telaAtual) {

                    "ONBOARDING" -> {
                        OnboardingScreen(
                            onMoradorClick = {
                                telaAtual = "LOGIN"
                            }
                        )
                    }

                    "LOGIN" -> {
                        LoginScreen(
                            onVoltarClick = {
                                telaAtual = "ONBOARDING"
                            },
                            onEntrarClick = { email, senha ->
                                // Navegação simulada para o protótipo.
                                // Futuramente validar email e senha.
                                telaAtual = "MINHAS_CASAS"
                            }
                        )
                    }

                    "MINHAS_CASAS" -> {
                        MyHousesScreen()
                    }
                }
            }
        }
    }
}
