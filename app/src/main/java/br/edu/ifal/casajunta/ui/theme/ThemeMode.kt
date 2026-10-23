
package br.edu.ifal.casajunta.ui.theme

enum class ThemeMode {
    System,
    Light,
    Dark,
}

fun ThemeMode.isDark(isSystemDark: Boolean): Boolean = when (this) {
    ThemeMode.System -> isSystemDark
    ThemeMode.Light -> false
    ThemeMode.Dark -> true
}

fun ThemeMode.toggled(isSystemDark: Boolean): ThemeMode =
    if (isDark(isSystemDark)) {
        ThemeMode.Light
    } else {
        ThemeMode.Dark
    }
