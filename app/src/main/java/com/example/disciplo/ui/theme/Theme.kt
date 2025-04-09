package com.example.disciplo.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.lightColorScheme


private val Yellow = Color(0xFFFFC107) // Amarelo vibrante
private val Black = Color(0xFF000000)  // Texto principal
private val White = Color(0xFFFFFFFF)  // Fundo

private val DisciploColorScheme = lightColorScheme(
    primary = Yellow,
    onPrimary = Black,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black
)

@Composable
fun DisciploTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DisciploColorScheme,
        typography = Typography(),
        content = content
    )
}
