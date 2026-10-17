
package br.edu.ifal.casajunta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.edu.ifal.casajunta.ui.screens.myHouses.MyHousesScreen
import br.edu.ifal.casajunta.ui.screens.myHouses.MyHousesScreen
import br.edu.ifal.casajunta.ui.screens.onboarding.OnboardingScreen
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CasaJuntaTheme {

                // Controla qual tela será exibida
                var telaAtual by remember {
                    mutableStateOf("ONBOARDING")
                }

                when (telaAtual) {

                    "ONBOARDING" -> {
                        OnboardingScreen(
                            onMoradorClick = {
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
