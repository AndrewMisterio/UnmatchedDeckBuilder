package andrew.misterio05.app

import andrew.misterio05.app.card.CardColors
import andrew.misterio05.app.card.CardData
import andrew.misterio05.app.card.CardView
import andrew.misterio05.app.theme.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun App() = AppTheme(useDarkTheme = true) {
    val borderColor = remember { Color(246, 234, 219) }
    CardView(
        modifier = Modifier.width(300.dp).background(borderColor),
        data = CardData(
            title = "Suiton: Suigadan",
            type = CardData.Type.Standard.atk(
                value = 3,
                description = CardData.Type.Description.Combat(
                    /*basic = "basic",
                    immediateText = "immediateText",
                    duringText = "duringText",*/
                    afterText = "afterText",
                )
            ),
            character = "ITACHI",
            boost = 1,
            imageUrl = "https://wiki.jcdn.ru/w/images/thumb/f/f3/Suigadan.jpg/450px-Suigadan.jpg",
            quantity = 2,
        ),
        appearance = CardColors(
            content = Color.White,
            background = Color.Black,
            border = borderColor,
        ),
    )
}

internal expect fun openUrl(url: String?)

internal expect fun font(res: String, weight: FontWeight, style: FontStyle): Font
