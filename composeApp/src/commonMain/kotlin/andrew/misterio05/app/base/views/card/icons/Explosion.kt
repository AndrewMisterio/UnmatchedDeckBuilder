package andrew.misterio05.app.base.views.card.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val CardIcons.ShieldExplosion: ImageVector
    get() {
        if (_shieldExplosion != null) {
            return _shieldExplosion!!
        }
        _shieldExplosion = ImageVector.Builder(
            name = "brightness_6",
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
                moveTo(14.583f, 33.083f)
                horizontalLineToRelative(-5f)
                quadToRelative(-1.083f, 0f, -1.854f, -0.771f)
                quadToRelative(-0.771f, -0.77f, -0.771f, -1.854f)
                verticalLineTo(25.5f)
                lineToRelative(-3.625f, -3.667f)
                quadToRelative(-0.75f, -0.75f, -0.75f, -1.833f)
                reflectiveQuadToRelative(0.75f, -1.833f)
                lineTo(6.958f, 14.5f)
                verticalLineTo(9.542f)
                quadToRelative(0f, -1.084f, 0.771f, -1.854f)
                quadToRelative(0.771f, -0.771f, 1.854f, -0.771f)
                horizontalLineToRelative(4.959f)
                lineToRelative(3.666f, -3.625f)
                quadToRelative(0.75f, -0.75f, 1.834f, -0.75f)
                quadToRelative(1.083f, 0f, 1.875f, 0.791f)
                lineTo(25.5f, 6.917f)
                horizontalLineToRelative(5f)
                quadToRelative(1.083f, 0f, 1.854f, 0.771f)
                quadToRelative(0.771f, 0.77f, 0.771f, 1.854f)
                verticalLineTo(14.5f)
                lineToRelative(3.625f, 3.667f)
                quadToRelative(0.75f, 0.75f, 0.75f, 1.833f)
                reflectiveQuadToRelative(-0.75f, 1.833f)
                lineTo(33.125f, 25.5f)
                verticalLineToRelative(4.958f)
                quadToRelative(0f, 1.084f, -0.771f, 1.854f)
                quadToRelative(-0.771f, 0.771f, -1.854f, 0.771f)
                horizontalLineToRelative(-5f)
                lineToRelative(-3.583f, 3.584f)
                quadToRelative(-0.75f, 0.75f, -1.855f, 0.75f)
                quadToRelative(-1.104f, 0f, -1.812f, -0.75f)
                close()
                moveToRelative(5.5f, -5.083f)
                quadToRelative(3.375f, 0f, 5.688f, -2.312f)
                quadToRelative(2.312f, -2.313f, 2.312f, -5.688f)
                quadToRelative(0f, -3.417f, -2.312f, -5.729f)
                quadToRelative(-2.313f, -2.313f, -5.688f, -2.313f)
                close()
            }
        }.build()
        return _shieldExplosion!!
    }

private var _shieldExplosion: ImageVector? = null
