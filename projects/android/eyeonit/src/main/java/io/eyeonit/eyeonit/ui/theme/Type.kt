package io.eyeonit.eyeonit.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.eyeonit.eyeonit.R

private val Segoe = FontFamily(
    Font(R.font.segoeui), //Norgmal
    Font(R.font.seguisb, FontWeight.W500), //Semi-bold
    Font(R.font.segoeuib, FontWeight.W600) //Bold
)

val EyeonitTypography = Typography(
    h4 = TextStyle(
        fontFamily = Segoe,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Segoe,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = Segoe,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Segoe,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Segoe,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Segoe,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = Segoe,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    )

)