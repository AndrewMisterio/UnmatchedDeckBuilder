package andrew.misterio05.app.card

import andrew.misterio05.app.font
import andrew.misterio05.app.theme.Image
import andrew.misterio05.app.urlPainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BorderWidth = 4.dp

// TODO by https://stackoverflow.com/questions/76035190/using-fonts-in-a-compose-multiplatform-project
private val cardTextStyle = TextStyle(
    fontFamily = FontFamily(
        font("bebas_neue_regular", FontWeight.Medium, FontStyle.Normal)
    )
)

@Composable
fun CardView(
    modifier: Modifier,
    data: CardData,
    contentColor: Color,
    backgroundColor: Color,
    borderColor: Color,
) {
    CompositionLocalProvider(LocalTextStyle provides cardTextStyle) {
        Box(modifier = modifier) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundColor)
            ) {
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = urlPainter(data.imageUrl),
                        contentDescription = "Image of card",
                        contentScale = ContentScale.Crop,
                    )
                    Shevron(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = -BorderWidth, y = -BorderWidth)
                            .border(
                                color = borderColor,
                                width = BorderWidth,
                            )
                            .background(backgroundColor),
                        icon = data.type.icon,
                        typeColor = data.type.color,
                        value = data.type.value,
                        character = data.character,
                        contentColor = contentColor,
                    )
                }

                Spacer(
                    Modifier.fillMaxWidth()
                        .height(BorderWidth)
                        .background(borderColor)
                )

                Spacer(
                    Modifier.fillMaxWidth()
                        .height(8.dp)
                        .background(backgroundColor)
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(horizontal = 16.dp),
                    text = data.title.uppercase(),
                    maxLines = 1,
                    style = LocalTextStyle.current.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Medium,
                    ),
                    color = contentColor,
                )

                Spacer(
                    Modifier.fillMaxWidth()
                        .height(8.dp)
                        .background(backgroundColor)
                )

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(backgroundColor)
                        .padding(horizontal = 16.dp)
                        .background(contentColor)
                )

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(backgroundColor)
                )

                Text(
                    modifier = Modifier
                        .background(backgroundColor)
                        .padding(horizontal = 16.dp),
                    text = data.type.description.basic,
                    color = contentColor,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(end = 8.dp),
                    text = "x${data.quantity}",
                    textAlign = TextAlign.End,
                    color = contentColor,
                )
            }
        }
    }
}

@Composable
private fun Shevron(
    modifier: Modifier = Modifier,
    icon: Image,
    typeColor: Color,
    contentColor: Color,
    value: Int,
    character: String,
) = Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    val textSize = 42

    Column(
        modifier = Modifier.background(typeColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 0.dp, top = 16.dp)
                .height(36.dp),
            painter = icon(),
            contentDescription = "icon of type",
            tint = contentColor,
        )
        if (value >= 0) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = value.toString(),
                color = contentColor,
                style = LocalTextStyle.current.copy(fontSize = textSize.sp),
                textAlign = TextAlign.Center,
            )
        }
    }
    Text(
        modifier = Modifier
            .padding(bottom = 16.dp, top = 8.dp)
            .vertical()
            .rotate(-90f),
        text = character.uppercase(),
        style = LocalTextStyle.current.copy(fontSize = textSize.sp),
        color = contentColor,
    )
}

fun Modifier.vertical() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.height, placeable.width) {
        placeable.place(
            x = -(placeable.width / 2 - placeable.height / 2),
            y = -(placeable.height / 2 - placeable.width / 2)
        )
    }
}