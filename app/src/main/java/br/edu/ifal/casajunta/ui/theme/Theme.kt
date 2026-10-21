package br.edu.ifal.casajunta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
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
    surfaceTint = Color.Transparent,
)

internal val CasaJuntaDarkColorScheme = darkColorScheme(
    primary = CasaJuntaDarkPrimary,
    onPrimary = CasaJuntaDarkOnPrimary,
    secondary = CasaJuntaDarkSecondary,
    onSecondary = CasaJuntaDarkOnSecondary,
    background = CasaJuntaDarkBackground,
    onBackground = CasaJuntaDarkOnBackground,
    surface = CasaJuntaDarkSurface,
    onSurface = CasaJuntaDarkOnSurface,
    surfaceVariant = CasaJuntaDarkSurfaceVariant,
    onSurfaceVariant = CasaJuntaDarkOnSurfaceVariant,
    error = CasaJuntaDarkError,
    onError = CasaJuntaDarkOnError,
    outline = CasaJuntaDarkOutline,
    surfaceTint = Color.Transparent,
)

private val CasaJuntaExtendedColorScheme = CasaJuntaExtendedColors(
    primaryDark = CasaJuntaPrimaryDark,
    primaryLight = CasaJuntaPrimaryLight,
    warning = CasaJuntaWarning,
)

private val CasaJuntaDarkExtendedColorScheme = CasaJuntaExtendedColors(
    primaryDark = CasaJuntaDarkPrimaryDark,
    primaryLight = CasaJuntaDarkPrimaryLight,
    warning = CasaJuntaDarkWarning,
)

object CasaJuntaTheme {
    val extendedColors: CasaJuntaExtendedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCasaJuntaExtendedColors.current
}

@Composable
fun CasaJuntaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) CasaJuntaDarkColorScheme else CasaJuntaColorScheme
    val extendedColors = if (darkTheme) {
        CasaJuntaDarkExtendedColorScheme
    } else {
        CasaJuntaExtendedColorScheme
    }
    CompositionLocalProvider(
        LocalCasaJuntaExtendedColors provides extendedColors,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}
