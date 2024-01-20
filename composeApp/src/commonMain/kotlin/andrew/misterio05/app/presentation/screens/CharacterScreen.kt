package andrew.misterio05.app.presentation.screens

import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.character.CharacterState
import andrew.misterio05.app.presentation.CARD_RATIO
import andrew.misterio05.app.presentation.views.TextField
import andrew.misterio05.app.presentation.views.card.CardColors
import andrew.misterio05.app.presentation.views.card.CardData
import andrew.misterio05.app.presentation.views.card.GameCardView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CharacterScreen(
    modifier: Modifier,
    screen: CharacterState,
    dispatch: (Event) -> Unit,
) {
    val paddings = 16.dp
    val arrangement = remember { Arrangement.spacedBy(paddings) }
    val colorScheme = MaterialTheme.colorScheme
    val cardAppearance = remember {
        CardColors(
            content = Color.White,
            background = Color.Black,
            border = colorScheme.onSecondaryContainer,
        )
    }
    LazyVerticalGrid(
        modifier = modifier.padding(paddings),
        columns = GridCells.Adaptive(200.dp),
        horizontalArrangement = arrangement,
        verticalArrangement = arrangement,
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                text = "",
                label = "Name",
                onTextChanged = {}
            )
        }
        items(screen.cards) {
            GameCardView(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.onSecondaryContainer),
                data = remember(it) {
                    CardData(
                        type = it.mapType(),
                        title = it.name,
                        character = it.character,
                        boost = it.boost,
                        imageUrl = it.image,
                        quantity = it.count,
                    )
                },
                appearance = cardAppearance,
            )
        }
    }
}

private fun CharacterState.Card.mapType(): CardData.Type {
    val combatDescription by lazy {
        CardData.Type.Description.Combat(
            basic = basicDescription,
            immediateText = immediateDescription,
            duringText = combatDescription,
            afterText = afterEffectDescription,
        )
    }
    return when (type) {
        CharacterState.Card.Type.ATK -> {
            CardData.Type.Standard.atk(
                value = value,
                description = combatDescription
            )
        }

        CharacterState.Card.Type.VER -> {
            CardData.Type.Standard.uni(
                value = value,
                description = combatDescription
            )
        }

        CharacterState.Card.Type.DEF -> {
            CardData.Type.Standard.def(
                value = value,
                description = combatDescription
            )
        }

        CharacterState.Card.Type.SCH -> {
            CardData.Type.Standard.sch(
                description = CardData.Type.Description.Basic(basicDescription)
            )
        }
    }
}
