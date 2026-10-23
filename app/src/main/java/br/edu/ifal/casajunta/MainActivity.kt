package br.edu.ifal.casajunta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import br.edu.ifal.casajunta.ui.theme.CasaJuntaTheme
import br.edu.ifal.casajunta.ui.theme.ThemeMode
import br.edu.ifal.casajunta.ui.theme.isDark
import br.edu.ifal.casajunta.ui.navigation.AppNavHost
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
            DisposableEffect(isDarkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ) { isDarkTheme },
                    navigationBarStyle = SystemBarStyle.auto(lightScrim, darkScrim) { isDarkTheme },
                )
                onDispose {}
            }
            CasaJuntaTheme(darkTheme = isDarkTheme) {
                AppNavHost(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = {
                        themeMode = themeMode.toggled(isSystemDark)
                    }
                )
            }
        }
    }
        private companion object {
        val lightScrim = 0xE6FFFFFF.toInt()
        val darkScrim = 0x801B1B1B.toInt()
    }
}
