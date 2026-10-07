package br.edu.ifal.casajunta.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val CasaJuntaColorScheme = lightColorScheme(

    // Cores principais
    primary = CasaJuntaPrimary,
    onPrimary = CasaJuntaTextOnPrimary,

    secondary = CasaJuntaSecondary,
    onSecondary = CasaJuntaTextOnPrimary,
    secondaryContainer = CasaJuntaClicado,

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
    outline = CasaJuntaBorder
)




@Composable
fun CasaJuntaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CasaJuntaColorScheme,
        typography = Typography,
        content = content
    )
}