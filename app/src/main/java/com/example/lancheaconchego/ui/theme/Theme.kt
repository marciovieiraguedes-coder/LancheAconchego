package com.example.lancheaconchego.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = TerracottaPrimary,
    onPrimary = CardSurface,
    primaryContainer = WarmLinen,
    onPrimaryContainer = EspressoDark,
    secondary = TerracottaLight,
    onSecondary = CardSurface,
    secondaryContainer = AmberGoldLight,
    onSecondaryContainer = EspressoDark,
    tertiary = AmberGold,
    background = CreamBackground,
    onBackground = EspressoDark,
    surface = CardSurface,
    onSurface = EspressoDark,
    surfaceVariant = WarmLinen,
    onSurfaceVariant = WarmBrown,
    outline = WarmBorderStrong,
    outlineVariant = WarmBorder
)

@Composable
fun LancheAconchegoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = CreamBackground.toArgb()
            window.navigationBarColor = CreamBackground.toArgb()
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = true
            insetsController.isAppearanceLightNavigationBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
