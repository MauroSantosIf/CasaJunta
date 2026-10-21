
package br.edu.ifal.casajunta.ui.theme

import br.edu.ifal.casajunta.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ============================================
// FONTES DO CASAJUNTA
// ============================================

// Mantida para compatibilidade com outras telas
val NunitoSans = FontFamily(
    Font(R.font.nunito_sans)
)

// Fonte principal do protótipo HTML
val CasaJuntaFont = FontFamily.SansSerif

// ============================================
// TIPOGRAFIA GLOBAL
// ============================================

val Typography = Typography(

    // Nome CasaJunta no cabeçalho
    headlineLarge = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 40.sp
    ),

    // Títulos principais das telas
    headlineMedium = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),

    // Títulos de seções
    titleLarge = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),

    // Títulos dos cartões
    titleMedium = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),

    // Títulos menores
    titleSmall = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // Texto de apresentação
    bodyLarge = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    // Descrições dos cartões
    bodyMedium = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // Textos auxiliares
    bodySmall = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),

    // Texto dos botões
    labelLarge = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),

    // Rótulos de campos
    labelMedium = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),

    // Rótulos pequenos
    labelSmall = TextStyle(
        fontFamily = CasaJuntaFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
)
