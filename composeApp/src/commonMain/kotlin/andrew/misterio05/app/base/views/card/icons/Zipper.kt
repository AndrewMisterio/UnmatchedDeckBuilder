package andrew.misterio05.app.base.views.card.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val CardIcons.Bolt: ImageVector
    get() {
        if (_bolt != null) {
            return _bolt!!
        }
        _bolt = ImageVector.Builder(
            name = "bolt",
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
                moveTo(17.667f, 34.792f)
                quadToRelative(-0.417f, 0f, -0.688f, -0.313f)
                quadToRelative(-0.271f, -0.312f, -0.187f, -0.729f)
                lineToRelative(1.541f, -10.542f)
                horizontalLineTo(12.5f)
                quadToRelative(-0.5f, 0f, -0.729f, -0.396f)
                quadToRelative(-0.229f, -0.395f, -0.021f, -0.812f)
                lineToRelative(9.667f, -16.25f)
                quadToRelative(0.125f, -0.208f, 0.416f, -0.375f)
                quadToRelative(0.292f, -0.167f, 0.584f, -0.167f)
                quadToRelative(0.416f, 0f, 0.687f, 0.292f)
                quadToRelative(0.271f, 0.292f, 0.188f, 0.708f)
                lineToRelative(-1.5f, 10.5f)
                horizontalLineToRelative(5.75f)
                quadToRelative(0.458f, 0f, 0.729f, 0.417f)
                quadToRelative(0.271f, 0.417f, 0.021f, 0.833f)
                lineToRelative(-9.584f, 16.25f)
                quadToRelative(-0.166f, 0.25f, -0.458f, 0.417f)
                quadToRelative(-0.292f, 0.167f, -0.583f, 0.167f)
                close()
            }
        }.build()
        return _bolt!!
    }

private var _bolt: ImageVector? = null
