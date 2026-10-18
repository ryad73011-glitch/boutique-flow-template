package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = GoldAccent,
  onPrimary = Charcoal900,
  primaryContainer = Charcoal700,
  onPrimaryContainer = GoldAccentLight,
  secondary = GoldAccentLight,
  onSecondary = Charcoal900,
  background = Charcoal900,
  onBackground = WarmCreamLight,
  surface = Charcoal800,
  onSurface = WarmCreamLight,
  surfaceVariant = Charcoal700,
  onSurfaceVariant = WarmCreamSurface,
  outline = Charcoal600,
  outlineVariant = Charcoal700,
  tertiary = TerracottaMuted,
  onTertiary = Color.White
)

private val LightColorScheme = lightColorScheme(
  primary = GoldDeep,
  onPrimary = Color.White,
  primaryContainer = WarmCreamCard,
  onPrimaryContainer = Charcoal900,
  secondary = GoldAccentDark,
  onSecondary = Color.White,
  background = WarmCreamLight,
  onBackground = Charcoal900,
  surface = WarmCreamSurface,
  onSurface = Charcoal900,
  surfaceVariant = WarmCreamCard,
  onSurfaceVariant = Charcoal700,
  outline = WarmCreamBorder,
  outlineVariant = WarmCreamBorder,
  tertiary = TerracottaMuted,
  onTertiary = Color.White
)

@Composable
fun MaisonElDjazairTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
