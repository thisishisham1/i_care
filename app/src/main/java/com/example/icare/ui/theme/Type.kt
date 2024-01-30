package com.example.icare.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.icare.R

val sfFontFamily = FontFamily(
    Font(R.font.sf_bold, FontWeight.Bold),
    Font(R.font.sf_light, FontWeight.Light),
    Font(R.font.sf_medium, FontWeight.Medium),
    Font(R.font.sf_regular, FontWeight.Normal),
    Font(R.font.sf_semibold, FontWeight.SemiBold),
    Font(R.font.sf_thin, FontWeight.Thin)
)
val gilroyFontFamily = FontFamily(
    Font(R.font.gilroy_bold, FontWeight.Bold),
    Font(R.font.gilroy_light, FontWeight.Light),
    Font(R.font.gilroy_medium, FontWeight.Medium),
    Font(R.font.gilroy_regular, FontWeight.Normal),
    Font(R.font.gilroy_semibold, FontWeight.SemiBold),
    Font(R.font.gilroy_thin, FontWeight.Thin)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleLarge = TextStyle(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    titleSmall = TextStyle(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
)