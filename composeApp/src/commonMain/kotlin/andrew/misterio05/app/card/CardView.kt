package andrew.misterio05.app.card

import andrew.misterio05.app.font
import andrew.misterio05.app.theme.Image
import andrew.misterio05.app.urlPainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BorderWidth = 4.dp

private val cardTitleStyle = TextStyle(
    fontFamily = FontFamily(
        font("bebas_neue_regular", FontWeight.Medium, FontStyle.Normal),
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both,
    ),
)
private val cardTextStyle = TextStyle(
    fontFamily = FontFamily(
        font("archivo_narrow", FontWeight.Medium, FontStyle.Normal),
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.Both,
    ),
)

@Composable
fun CardView(
    modifier: Modifier,
    data: CardData,
    contentColor: Color,
    backgroundColor: Color,
    borderColor: Color,
) = CompositionLocalProvider(
    LocalTextStyle provides cardTextStyle,
    LocalContentColor provides contentColor,
) {
    Box(modifier = modifier) {
        var boostPositionY by remember { mutableStateOf(0) }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
        ) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
            ) {
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
                )
            }

            Spacer(
                Modifier.fillMaxWidth()
                    .height(BorderWidth)
                    .background(borderColor)
                    .onPlaced {
                        boostPositionY = (it.positionInParent().y + it.parentLayoutCoordinates?.positionInParent()?.y!!).toInt()
                    }
            )

            Spacer(Modifier.fillMaxWidth().height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = data.title.uppercase(),
                maxLines = 1,
                style = cardTitleStyle.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Medium,
                ),
                color = contentColor,
            )

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(horizontal = 16.dp)
                    .background(contentColor)
            )

            Spacer(Modifier.fillMaxWidth().height(8.dp))

            DescriptionText(
                modifier = Modifier.padding(horizontal = 16.dp),
                data = data.type.description,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                text = "x${data.quantity}",
                textAlign = TextAlign.End,
                color = contentColor,
            )
        }

        BoostCell(
            modifier = Modifier.offset { IntOffset(x = boostPositionY, y = boostPositionY) },
            value = data.boost,
            backgroundColor = backgroundColor,
            borderColor = borderColor,
        )
    }
}

@Composable
private fun BoostCell(
    modifier: Modifier,
    backgroundColor: Color,
    borderColor: Color,
    value: Int,
) {
    val shape = remember { RoundedCornerShape(percent = 50) }
    var height by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Text(
        modifier = modifier
            .offset(y = -height/2)
            .clip(shape)
            .onSizeChanged { height = density.run { it.height.toDp() } }
            .width(height)
            .background(backgroundColor)
            .border(BorderWidth, borderColor, shape)
            .padding(4.dp),
        text = value.toString(),
        style = cardTitleStyle.copy(fontSize = 24.sp),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun DescriptionText(
    modifier: Modifier,
    data: CardData.Type.Description,
) = Column(modifier) {
    @Composable
    fun annotation(part: String, value: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = buildAnnotatedString {
            if (part.isNotEmpty()) {
                withStyle(remember {
                    cardTitleStyle.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp
                    ).toSpanStyle()
                }) {
                    append("$part: ")
                }
            }
            withStyle(remember { cardTextStyle.copy(fontSize = 24.sp).toSpanStyle() }) {
                append(value)
            }
        },
    )
    if (data.basic.isNotEmpty()) {
        annotation(
            part = "",
            value = data.basic.trim(),
        )
    }
    if (data is CardData.Type.Description.Combat) {
        if (data.immediateText.isNotEmpty()) {
            annotation("Immediate", data.immediateText)
        }
        if (data.duringText.isNotEmpty()) {
            annotation("During combat", data.duringText)
        }
        if (data.afterText.isNotEmpty()) {
            annotation("After combat", data.afterText)
        }
    }
}

@Composable
private fun Shevron(
    modifier: Modifier = Modifier,
    icon: Image,
    typeColor: Color,
    value: Int,
    character: String,
) = Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
    CompositionLocalProvider(LocalTextStyle provides cardTitleStyle.copy(fontSize = 42.sp)) {
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
            )
            if (value >= 0) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = value.toString(),
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
        )
    }
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