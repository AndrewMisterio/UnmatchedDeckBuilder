package andrew.misterio05.app.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import io.github.skeptick.libres.compose.painterResource

fun interface Image {

    @Composable
    operator fun invoke(): Painter

    companion object {
        fun vector(imageVector: ImageVector) = Image { rememberVectorPainter(imageVector) }

        fun resource(image: io.github.skeptick.libres.images.Image) =
            Image { image.painterResource() }
    }
}