package andrew.misterio05.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.browser.window

internal actual fun openUrl(url: String?) {
    url?.let { window.open(it) }
}

@Composable
internal actual fun font(
    res: String,
    weight: FontWeight,
    style: FontStyle
): Font {
    TODO("Not yet implemented")
}