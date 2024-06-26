package andrew.misterio05.app.base.views.card

import andrew.misterio05.app.base.CARD_RATIO
import andrew.misterio05.app.base.theme.Image
import andrew.misterio05.app.base.urlPainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameCardView(
    modifier: Modifier,
    data: CardData,
    appearance: CardColors,
) {
    var size by remember { mutableStateOf(DpSize.Zero) }
    // 400 is the benchmark card size.
    val scaleFactor = remember(size.width) { size.width / 400.dp }
    val density = LocalDensity.current

    CompositionLocalProvider(
        LocalTextStyle provides cardTextStyle,
        LocalContentColor provides appearance.content,
        CardTheme.provideColors(appearance),
        CardTheme.provideFonts(createCardFonts(scaleFactor)),
        CardTheme.providePaddings(createCardPaddings(scaleFactor)),
    ) {
        var boostPosition by remember { mutableStateOf(IntOffset.Zero) }
        Box(
            modifier = modifier
                .aspectRatio(CARD_RATIO)
                .padding(CardTheme.paddings.medium)
                .onSizeChanged {
                    size = density.run { DpSize(it.width.toDp(), it.height.toDp()) }
                },
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(CardTheme.paddings.small))
                    .background(CardTheme.colors.background)
            ) {
                Image(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    painter = urlPainter(data.imageUrl),
                    contentDescription = "Image of card",
                    contentScale = ContentScale.Crop,
                )

                Spacer(
                    modifier = Modifier.fillMaxWidth()
                        .height(CardTheme.paddings.tiny)
                        .background(CardTheme.colors.border)
                        .onPlaced {
                            val positionInParent = it.parentLayoutCoordinates?.positionInParent()!!
                            boostPosition = IntOffset(
                                y = (it.positionInParent().y + positionInParent.y).toInt(),
                                x = -positionInParent.x.toInt(),
                            )
                        }
                )

                Spacer(Modifier.fillMaxWidth().height(CardTheme.paddings.small))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = CardTheme.paddings.medium),
                    text = data.title.uppercase(),
                    maxLines = 1,
                    style = CardTheme.fonts.h2.copy(fontWeight = FontWeight.Medium),
                )

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(horizontal = CardTheme.paddings.medium)
                        .background(CardTheme.colors.content)
                )

                Spacer(Modifier.fillMaxWidth().height(CardTheme.paddings.small))

                DescriptionText(
                    modifier = Modifier.padding(horizontal = CardTheme.paddings.medium),
                    data = data.type.description,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = CardTheme.paddings.small),
                    text = "x${data.quantity}",
                    textAlign = TextAlign.End,
                    style = CardTheme.fonts.body2,
                )
            }

            ChevronView(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = -CardTheme.paddings.tiny, y = -CardTheme.paddings.tiny)
                    .clip(ChevronShape)
                    .border(
                        color = CardTheme.colors.border,
                        width = CardTheme.paddings.tiny,
                        shape = ChevronShape,
                    )
                    .background(CardTheme.colors.background),
                icon = data.type.icon,
                typeColor = data.type.color,
                value = data.type.value,
                character = data.character,
            )
            // Boost circle
            val shape = remember { RoundedCornerShape(percent = 50) }
            var height by remember { mutableStateOf(0.dp) }
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset { boostPosition }
                    .padding(end = CardTheme.paddings.small)
                    .offset(y = -height / 2)
                    .clip(shape)
                    .onSizeChanged { height = density.run { it.height.toDp() } }
                    .width(height)
                    .background(CardTheme.colors.background)
                    .border(CardTheme.paddings.tiny, CardTheme.colors.border, shape)
                    .padding(CardTheme.paddings.tiny),
                text = data.boost.toString(),
                style = CardTheme.fonts.body1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

private fun createCardPaddings(scaleFactor: Float) = CardPaddings(
    small = 8.dp * scaleFactor,
    medium = 16.dp * scaleFactor,
    tiny = 4.dp * scaleFactor,
)

private fun createCardFonts(scaleFactor: Float) = CardFonts(
    h1 = cardTitleStyle.copy(fontSize = 42.sp * scaleFactor),
    h2 = cardTitleStyle.copy(fontSize = 36.sp * scaleFactor),
    body1 = cardTitleStyle.copy(fontSize = 24.sp * scaleFactor),
    body2 = cardTextStyle.copy(fontSize = 24.sp * scaleFactor),
)

@Composable
private fun DescriptionText(
    modifier: Modifier,
    data: CardData.Type.Description,
) = Column(modifier) {
    @Composable
    fun annotation(part: String, value: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = buildAnnotatedString {
            val fonts = CardTheme.fonts
            if (part.isNotEmpty()) {
                val partStyle = remember(fonts.body1) {
                    fonts.body1.copy(fontWeight = FontWeight.Medium).toSpanStyle()
                }
                withStyle(partStyle) { append("$part: ") }
            }
            val textStyle = remember(fonts.body2) { fonts.body2.toSpanStyle() }
            withStyle(textStyle) { append(value) }
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
private fun ChevronView(
    modifier: Modifier = Modifier,
    icon: Image,
    typeColor: Color,
    value: Int,
    character: String,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    val padding = CardTheme.paddings.medium
    Column(
        modifier = Modifier
            .clip(ChevronShape)
            .background(typeColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = padding)
                .padding(top = padding)
                .height(LocalDensity.current.run { CardTheme.fonts.h1.fontSize.toDp() }),
            painter = icon(),
            contentDescription = "icon of type",
        )
        if (value >= 0) {
            Text(
                modifier = Modifier.padding(horizontal = padding),
                text = value.toString(),
                textAlign = TextAlign.Center,
                style = CardTheme.fonts.h1,
            )
        } else {
            Spacer(Modifier.size(padding))
        }
    }
    Spacer(Modifier.height(CardTheme.paddings.small))
    Text(
        modifier = Modifier
            .padding(bottom = padding, top = CardTheme.paddings.small)
            .vertical()
            .rotate(-90f),
        text = character.uppercase(),
        maxLines = 1,
        overflow = TextOverflow.Visible,
        style = CardTheme.fonts.h1,
    )
    Spacer(Modifier.height(padding))
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