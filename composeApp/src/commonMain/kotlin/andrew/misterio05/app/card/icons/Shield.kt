package andrew.misterio05.app.card.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val CardIcons.Shield: ImageVector
    get() {
        if (_shield != null) {
            return _shield!!
        }
        _shield = ImageVector.Builder(
            name = "shield",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20f, 36.25f)
                quadToRelative(-0.167f, 0f, -0.333f, -0.021f)
                quadToRelative(-0.167f, -0.021f, -0.292f, -0.062f)
                quadToRelative(-5.458f, -1.625f, -8.958f, -6.709f)
                quadToRelative(-3.5f, -5.083f, -3.5f, -11.166f)
                verticalLineToRelative(-7.959f)
                quadToRelative(0f, -0.833f, 0.479f, -1.521f)
                quadToRelative(0.479f, -0.687f, 1.229f, -0.979f)
                lineToRelative(10.458f, -3.916f)
                quadToRelative(0.459f, -0.167f, 0.917f, -0.167f)
                reflectiveQuadToRelative(0.917f, 0.167f)
                lineToRelative(10.458f, 3.916f)
                quadToRelative(0.75f, 0.292f, 1.229f, 0.979f)
                quadToRelative(0.479f, 0.688f, 0.479f, 1.521f)
                verticalLineToRelative(7.959f)
                quadToRelative(0f, 6.083f, -3.5f, 11.166f)
                quadToRelative(-3.5f, 5.084f, -8.958f, 6.709f)
                lineTo(20f, 36.25f)
                close()
            }
        }.build()
        return _shield!!
    }

private var _shield: ImageVector? = null
