
package br.edu.ifal.casajunta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// ============================================
// CASAJUNTA - TEMA CLARO
// ============================================

private val CasaJuntaLightColorScheme = lightColorScheme(

    // Cores principais
    primary = CasaJuntaPrimary,
    onPrimary = CasaJuntaTextOnPrimary,
    primaryContainer = CasaJuntaPrimaryLight,
    onPrimaryContainer = CasaJuntaTextPrimary,

    // Cores secundárias
    secondary = CasaJuntaSecondary,
    onSecondary = CasaJuntaTextOnPrimary,
    secondaryContainer = CasaJuntaClicado,
    onSecondaryContainer = CasaJuntaTextPrimary,

    // Fundo principal
    background = CasaJuntaBackground,
    onBackground = CasaJuntaTextPrimary,

    // Superfícies e cartões
    surface = CasaJuntaSurface,
    onSurface = CasaJuntaTextPrimary,

    surfaceVariant = CasaJuntaSurfaceVariant,
    onSurfaceVariant = CasaJuntaTextSecondary,

    surfaceContainerLowest = CasaJuntaSurface,
    surfaceContainerLow = CasaJuntaSurface,
    surfaceContainer = CasaJuntaSurfaceVariant,
    surfaceContainerHigh = CasaJuntaSurfaceVariant,
    surfaceContainerHighest = CasaJuntaSurfaceVariant,

    // Bordas
    outline = CasaJuntaBorder,
    outlineVariant = CasaJuntaBorder,

    // Erros
    error = CasaJuntaError,
    onError = CasaJuntaTextOnPrimary,
    errorContainer = CasaJuntaErrorLight,
    onErrorContainer = CasaJuntaTextPrimary
)


// ============================================
// CASAJUNTA - TEMA ESCURO
// ============================================

private val CasaJuntaDarkColorScheme = darkColorScheme(

    // Cores principais
    primary = CasaJuntaDarkPrimary,
    onPrimary = CasaJuntaDarkTextOnPrimary,
    primaryContainer = CasaJuntaDarkPrimaryContainer,
    onPrimaryContainer = CasaJuntaDarkTextPrimary,

    // Cores secundárias
    secondary = CasaJuntaDarkPrimary,
    onSecondary = CasaJuntaDarkTextOnPrimary,
    secondaryContainer = CasaJuntaDarkClicado,
    onSecondaryContainer = CasaJuntaDarkTextPrimary,

    // Fundo principal
    background = CasaJuntaDarkBackground,
    onBackground = CasaJuntaDarkTextPrimary,

    // Superfícies e cartões
    surface = CasaJuntaDarkSurface,
    onSurface = CasaJuntaDarkTextPrimary,

    surfaceVariant = CasaJuntaDarkSurfaceVariant,
    onSurfaceVariant = CasaJuntaDarkTextSecondary,

    surfaceContainerLowest = CasaJuntaDarkBackground,
    surfaceContainerLow = CasaJuntaDarkSurface,
    surfaceContainer = CasaJuntaDarkSurface,
    surfaceContainerHigh = CasaJuntaDarkSurface,
    surfaceContainerHighest = CasaJuntaDarkSurface,

    // Bordas
    outline = CasaJuntaDarkBorder,
    outlineVariant = CasaJuntaDarkBorder,

    // Erros
    error = CasaJuntaError,
    onError = CasaJuntaDarkTextPrimary,
    errorContainer = CasaJuntaDarkClicado,
    onErrorContainer = CasaJuntaDarkTextPrimary
)


// ============================================
// TEMA PRINCIPAL DO APLICATIVO
// ============================================

@Composable
fun CasaJuntaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        CasaJuntaDarkColorScheme
    } else {
        CasaJuntaLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
