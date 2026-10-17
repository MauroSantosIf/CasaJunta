package br.edu.ifal.casajunta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.edu.ifal.casajunta.ui.CasaJuntaApp
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CasaJuntaTheme {
                CasaJuntaApp()
            }
        }
    }
}
