package br.edu.ifal.casajunta.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CasaJuntaExtendedColors(
    val primaryDark: Color,
    val primaryLight: Color,
    val warning: Color,
)

internal val LocalCasaJuntaExtendedColors = staticCompositionLocalOf<CasaJuntaExtendedColors> {
    error("CasaJuntaExtendedColors não foi fornecido. Envolva o conteúdo com CasaJuntaTheme.")
}
