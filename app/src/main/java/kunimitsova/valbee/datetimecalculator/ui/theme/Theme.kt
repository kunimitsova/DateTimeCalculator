package kunimitsova.valbee.datetimecalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DeepOrange200_dark,
    primaryVariant = Orange100_dark,
    secondary = Teal100_dark,
    surface = BlueGray900,
    background = Gray900
)

private val LightColorPalette = lightColors(
    primary = Red200,
    primaryVariant = Amber100,
    secondary = Teal50,
    surface = Pink45,
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