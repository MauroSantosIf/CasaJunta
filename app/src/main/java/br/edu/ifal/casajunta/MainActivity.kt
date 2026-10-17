package br.edu.ifal.casajunta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import br.edu.ifal.casajunta.ui.CasaJuntaApp
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme
import br.edu.ifal.casajunta.ui.theme.ThemeMode
import br.edu.ifal.casajunta.ui.theme.isDark
import br.edu.ifal.casajunta.ui.theme.toggled

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var themeMode by rememberSaveable(
                stateSaver = Saver(
                    save = { it.name },
                    restore = { name -> ThemeMode.valueOf(name) },
                ),
            ) {
                mutableStateOf(ThemeMode.System)
            }
            val isSystemDark = isSystemInDarkTheme()
            val isDarkTheme = themeMode.isDark(isSystemDark)
            CasaJuntaTheme(darkTheme = isDarkTheme) {
                CasaJuntaApp(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = { themeMode = themeMode.toggled(isSystemDark) },
                )
            }
        }
    }
}
