package kunimitsova.valbee.datetimecalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// my palette uses Secondary as a secondary surface color for now.
private val DarkColorPalette = darkColors(
    primary = Brown700_dark,
    primaryVariant = DeepOrange200_dark,
    secondary = Teal900_dark,
    surface = Brown700_dark,
    background = Gray900,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.White,
    onBackground = Color.White
)

private val LightColorPalette = lightColors(
    primary = Red200,
    primaryVariant = Red200_dark,
    secondary = Teal50,
    surface = Red200_light,
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    secondaryVariant = DeepOrange200_dark

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun DateTimeCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}