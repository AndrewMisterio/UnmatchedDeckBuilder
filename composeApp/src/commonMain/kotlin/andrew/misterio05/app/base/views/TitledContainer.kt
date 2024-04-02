package andrew.misterio05.app.base.views

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun OutlineTitledContainer(
    modifier: Modifier = Modifier,
    title: String,
    contentColor: Color = LocalContentColor.current,
    content: @Composable BoxScope.() -> Unit,
) = CompositionLocalProvider(LocalContentColor provides contentColor) {
    var position by remember { mutableStateOf(Offset.Zero) }
    var size by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawRoundRect(
                        color = Color.Black,
                        topLeft = position,
                        size = size,
                        blendMode = BlendMode.Clear,
                    )
                }
            }
            .border(
                width = 2.dp,
                color = LocalContentColor.current,
                shape = RoundedCornerShape(8.dp),
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {

        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .onSizeChanged { size = it.toSize() }
                .onPlaced { position = it.positionInParent() },
            text = title,
            color = LocalContentColor.current,
        )

        content(this)
    }
}