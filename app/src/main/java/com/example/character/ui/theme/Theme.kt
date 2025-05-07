package com.example.character.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple500,
    secondary = Teal200,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Purple500,
    secondary = Teal200,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val MidnightColorScheme = darkColorScheme(
    primary = MidnightBlue,
    secondary = DeepPurple,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    background = Color(0xFF1A1A1A),
    surface = Color(0xFF2C2C2C)
)

private val BrownColorScheme = lightColorScheme(
    primary = Brown,
    secondary = Tan,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    background = Cream,
    surface = Tan
)

private val PinkColorScheme = lightColorScheme(
    primary = Pink,
    secondary = LightPink,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

enum class AppTheme {
    DEFAULT,
    DARK,
    MIDNIGHT,
    BROWN,
    PINK
}

@Composable
fun CharacterTheme(
    theme: AppTheme = AppTheme.DEFAULT,
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        AppTheme.DEFAULT -> {
            if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
        }
        AppTheme.DARK -> DarkColorScheme
        AppTheme.MIDNIGHT -> MidnightColorScheme
        AppTheme.BROWN -> BrownColorScheme
        AppTheme.PINK -> PinkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}