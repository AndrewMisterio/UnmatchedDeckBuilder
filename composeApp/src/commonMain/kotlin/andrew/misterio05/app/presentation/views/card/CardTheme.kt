package andrew.misterio05.app.presentation.views.card

import andrew.misterio05.app.font
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

object CardTheme {
    val colors @Composable get() = LocalCardColors.current
    val fonts @Composable get() = LocalCardFonts.current
    val paddings @Composable get() = LocalCardPaddings.current

    fun provideColors(appearance: CardColors) = LocalCardColors provides appearance
    fun provideFonts(fonts: CardFonts) = LocalCardFonts provides fonts
    fun providePaddings(paddings: CardPaddings) = LocalCardPaddings provides paddings
}

data class CardPaddings(
    val small: Dp,
    val medium: Dp,
    val tiny: Dp,
)

private val LocalCardPaddings = compositionLocalOf {
    CardPaddings(
        small = 8.dp,
        medium = 16.dp,
        tiny = 4.dp,
    )
}

private val LocalCardFonts = compositionLocalOf {
    CardFonts(
        h1 = cardTitleStyle.copy(fontSize = 42.sp),
        h2 = cardTitleStyle.copy(fontSize = 36.sp),
        body1 = cardTitleStyle.copy(
            fontSize = 24.sp,
            lineHeight = 0.em,
            letterSpacing = 0.sp,
        ),
        body2 = cardTextStyle.copy(
            fontSize = 24.sp,
            lineHeight = 0.em,
            letterSpacing = 0.sp,
        ),
    )
}

data class CardFonts(
    val h1: TextStyle,
    val h2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
)

private val LocalCardColors = compositionLocalOf {
    CardColors(
        content = Color.White,
        background = Color.Black,
        border = Color(246, 234, 219),
    )
}

data class CardColors(
    val content: Color,
    val background: Color,
    val border: Color,
)

internal val cardTitleStyle = TextStyle(
    fontFamily = FontFamily(
        font("bebas_neue_regular", FontWeight.Medium, FontStyle.Normal),
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both,
    ),
)
internal val cardTextStyle = TextStyle(
    fontFamily = FontFamily(
        font("archivo_narrow", FontWeight.Medium, FontStyle.Normal),
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both,
    ),
)