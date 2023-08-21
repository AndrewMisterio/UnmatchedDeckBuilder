package andrew.misterio05.app.card

import androidx.compose.foundation.shape.GenericShape

val ChevronShape = GenericShape { size, _ ->
    lineTo(size.width, 0f)
    val bottomAngles = size.height - size.width / 3
    lineTo(size.width, bottomAngles)
    lineTo(size.width / 2, size.height)
    lineTo(0f, bottomAngles)
}
