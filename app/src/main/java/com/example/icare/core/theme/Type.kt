package com.example.icare.core.theme

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

val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp, // Increased for better prominence
        lineHeight = 36.sp // Added line height for better readability
    ), headlineMedium = TextStyle(
        fontFamily = gilroyFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp, // Increased for better readability
        lineHeight = 30.sp
    ), titleLarge = TextStyle(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp, // Adjusted for better balance
        lineHeight = 24.sp
    ), titleMedium = TextStyle(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp, // Adjusted for better balance
        lineHeight = 20.sp
    ), titleSmall = TextStyle(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp, // Adjusted for better balance
        lineHeight = 18.sp
    ), bodyLarge = TextStyle( // Added bodyLarge style
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ), bodyMedium = TextStyle( // Added bodyMedium style
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ), bodySmall = TextStyle( // Added bodySmall style
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ), labelLarge = TextStyle(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ), labelMedium = TextStyle(
        fontFamily = sfFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ), labelSmall = TextStyle(
        fontFamily = sfFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp
    )
)