package br.edu.ifal.casajunta.ui.theme

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ThemeModeTest {

    @Test
    fun systemFollowsLightSystemTheme() {
        assertFalse(ThemeMode.System.isDark(isSystemDark = false))
        assertEquals(ThemeMode.Dark, ThemeMode.System.toggled(isSystemDark = false))
    }

    @Test
    fun systemFollowsDarkSystemTheme() {
        assertTrue(ThemeMode.System.isDark(isSystemDark = true))
        assertEquals(ThemeMode.Light, ThemeMode.System.toggled(isSystemDark = true))
    }

    @Test
    fun lightStaysLightUntilToggled() {
        assertFalse(ThemeMode.Light.isDark(isSystemDark = false))
        assertFalse(ThemeMode.Light.isDark(isSystemDark = true))
        assertEquals(ThemeMode.Dark, ThemeMode.Light.toggled(isSystemDark = false))
        assertEquals(ThemeMode.Dark, ThemeMode.Light.toggled(isSystemDark = true))
    }

    @Test
    fun darkStaysDarkUntilToggled() {
        assertTrue(ThemeMode.Dark.isDark(isSystemDark = false))
        assertTrue(ThemeMode.Dark.isDark(isSystemDark = true))
        assertEquals(ThemeMode.Light, ThemeMode.Dark.toggled(isSystemDark = false))
        assertEquals(ThemeMode.Light, ThemeMode.Dark.toggled(isSystemDark = true))
    }
}
