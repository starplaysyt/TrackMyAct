package org.js.tma

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import trackmyact.composeapp.generated.resources.*

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF573139),
    onPrimary = Color(0xFFdfc8cc),
    background = Color(0xFFf5eff0),
    onBackground = Color(0xFFCFFAF0),
    surface = Color(0xFFf5eff0),
    onSurface = Color(0xFFBE99A1),
    surfaceVariant = Color(0xFF854e58),
    primaryContainer = Color(0xFFC89BA2),
    outline = Color(0xFF573139),
    outlineVariant = Color(0xFF6CB5A7),
    error = Color(0xFFC74949),
    secondaryContainer = Color(0xFF573139)
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF86dfce),
    onPrimary = Color(0xFF10221f),
    background = Color(0xFF2e171c),
    onBackground = Color(0xFF10221F),
    surface = Color(0xFF86dfce),
    onSurface = Color(0xFF10221F),
    surfaceVariant = Color(0xFFcffaf0),
    primaryContainer = Color(0xFF6CB5A7),
    outline = Color(0xFFEEF1F1),
    outlineVariant = Color(0xFFA4A9A8),
    error = Color(0xFFC74949),
    secondaryContainer = Color(0xFF25433D),
)

data class IndicatorColors(
    val error: Color,
    val warning: Color,
    val success: Color,
)

val AppColors = IndicatorColors(
    error = Color(0xFFC74949),
    warning = Color(0xFFE5C237),
    success = Color(0xFF55B442),
)

val AppShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(30.dp)
)

@Composable
fun getAppFontFamily() = FontFamily(
    Font(Res.font.Nunito_ExtraBold, FontWeight.ExtraBold),
    Font(Res.font.Nunito_Regular, FontWeight.Normal),
    Font(Res.font.Nunito_Medium, FontWeight.Medium),
    Font(Res.font.Nunito_Light, FontWeight.Light),
    Font(Res.font.Nunito_Black, FontWeight.Black),
    Font(Res.font.Nunito_Bold, FontWeight.Bold),
)

@Composable
fun getBackground() : Modifier {
    val colors = MaterialTheme.colorScheme.background to MaterialTheme.colorScheme.onBackground

    return Modifier.background(
        brush = Brush.verticalGradient(
            colors = listOf(
                colors.first,
                colors.second,
            )
        )
    )
}

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(
            labelSmall = TextStyle(fontFamily = getAppFontFamily(), fontWeight = FontWeight.Bold, fontSize = 11.sp),
            labelMedium = TextStyle(fontFamily = getAppFontFamily(), fontWeight = FontWeight.ExtraBold, fontSize = 15.sp, color = colorScheme.onPrimary),
            labelLarge = TextStyle(fontFamily = getAppFontFamily(), fontWeight = FontWeight.Black, fontSize = 14.sp, color = colorScheme.onPrimary),

            bodyMedium = TextStyle(fontFamily = getAppFontFamily(), fontWeight = FontWeight.Normal, fontSize = 15.sp, color = colorScheme.onSurface.copy(alpha = 0.5f)),

            titleSmall = TextStyle(fontFamily = getAppFontFamily(), fontWeight = FontWeight.Light, fontSize = 14.sp, color = colorScheme.primary),
            titleMedium = TextStyle(fontFamily = getAppFontFamily(), fontWeight = FontWeight.Medium, fontSize = 13.sp, color = colorScheme.primary),
            titleLarge = TextStyle(fontFamily = getAppFontFamily(), fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, color = colorScheme.primary),
            ),
        shapes = AppShapes,
        content = content
    )
}