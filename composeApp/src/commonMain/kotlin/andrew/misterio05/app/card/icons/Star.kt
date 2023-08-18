package andrew.misterio05.app.card.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val CardIcons.Star: ImageVector
    get() {
        if (_star != null) {
            return _star!!
        }
        _star = ImageVector.Builder(
            name = "shieldExplosion",
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
                moveTo(11.667f, 34.958f)
                quadToRelative(-0.417f, 0.334f, -0.813f, 0.042f)
                quadToRelative(-0.396f, -0.292f, -0.229f, -0.75f)
                lineToRelative(3.167f, -10.292f)
                lineTo(5.5f, 18f)
                quadToRelative(-0.375f, -0.25f, -0.229f, -0.729f)
                reflectiveQuadToRelative(0.646f, -0.479f)
                horizontalLineToRelative(10.208f)
                lineTo(19.375f, 6f)
                quadToRelative(0.083f, -0.208f, 0.25f, -0.333f)
                quadToRelative(0.167f, -0.125f, 0.375f, -0.125f)
                reflectiveQuadToRelative(0.375f, 0.125f)
                quadToRelative(0.167f, 0.125f, 0.25f, 0.333f)
                lineToRelative(3.25f, 10.792f)
                horizontalLineToRelative(10.208f)
                quadToRelative(0.5f, 0f, 0.646f, 0.479f)
                quadToRelative(0.146f, 0.479f, -0.229f, 0.729f)
                lineToRelative(-8.292f, 5.958f)
                lineToRelative(3.167f, 10.292f)
                quadToRelative(0.167f, 0.458f, -0.229f, 0.75f)
                reflectiveQuadToRelative(-0.771f, -0.042f)
                lineTo(20f, 28.625f)
                close()
            }
        }.build()
        return _star!!
    }

private var _star: ImageVector? = null
