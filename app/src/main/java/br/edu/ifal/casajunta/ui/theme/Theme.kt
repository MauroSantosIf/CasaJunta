package br.edu.ifal.casajunta.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val CasaJuntaColorScheme = lightColorScheme(

    // Cores principais
    primary = CasaJuntaPrimary,
    onPrimary = CasaJuntaTextOnPrimary,

    secondary = CasaJuntaSecondary,
    onSecondary = CasaJuntaTextOnPrimary,

    // Fundo
    background = CasaJuntaBackground,
    onBackground = CasaJuntaTextPrimary,

    // Cards e superfícies
    surface = CasaJuntaSurface,
    onSurface = CasaJuntaTextPrimary,

    surfaceVariant = CasaJuntaSurfaceVariant,
    onSurfaceVariant = CasaJuntaTextSecondary,

    // Erros
    error = CasaJuntaError,
    onError = CasaJuntaTextOnPrimary,

    // Bordas
    outline = CasaJuntaBorder,
)

private val CasaJuntaExtendedColorScheme = CasaJuntaExtendedColors(
    primaryDark = CasaJuntaPrimaryDark,
    primaryLight = CasaJuntaPrimaryLight,
    warning = CasaJuntaWarning,
)

object CasaJuntaTheme {
    val extendedColors: CasaJuntaExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCasaJuntaExtendedColors.current
}

@Composable
fun CasaJuntaTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalCasaJuntaExtendedColors provides CasaJuntaExtendedColorScheme,
    ) {
        MaterialTheme(
            colorScheme = CasaJuntaColorScheme,
            typography = Typography,
            content = content,
        )
    }
}
