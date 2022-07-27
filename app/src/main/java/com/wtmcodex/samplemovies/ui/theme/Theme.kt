package com.wtmcodex.samplemovies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ColorPalette = lightColorScheme(
    primary = PinkA400,
    surfaceVariant = PinkA700,
    secondary = Black,
    onPrimary = White,
    onSecondary = White,
    background = White,
    surface = Black,
    onBackground = White,
    onSurface = White,
)

@Composable
fun SampleMoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = ColorPalette

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}