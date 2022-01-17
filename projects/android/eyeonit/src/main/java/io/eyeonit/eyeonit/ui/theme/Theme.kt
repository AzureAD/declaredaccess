package io.eyeonit.eyeonit.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//I'm not going to pretend to know how to use color properly... deferring to the parent on this one
//Reference: https://microsoft.sharepoint.com/teams/brandcentral/Pages/The-Microsoft-brand-Core-elements-Type.aspx

private val LightColors = lightColors(
    primary = Blue,
    primaryVariant = DarkBlue,
    onPrimary = White,
    secondary = Blue,
    secondaryVariant = DarkBlue,
    onSecondary = White,
    error = Orange
)

private val DarkColors = darkColors(
    primary = White,
    primaryVariant = LightBlue,
    onPrimary = Black,
    secondary = White,
    onSecondary = Black,
    error = Orange
)

@Composable
fun EyeonitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        typography = EyeonitTypography,
        shapes = EyeonitShapes,
        content = content
    )
}