package andrew.misterio05.app.presentation.screens

import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.character.CharacterState
import andrew.misterio05.app.presentation.views.TextField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterScreen(
    modifier: Modifier,
    screen: CharacterState,
    dispatch: (Event) -> Unit,
) = Column(
    modifier = modifier
        .padding(16.dp),
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        text = "",
        label = "Name",
        onTextChanged = {}
    )

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(100.dp),
    ) {
        items(screen.cards) {
            Card(
                modifier = Modifier.padding(4.dp),
            ) {
                Text(text = it.name)
            }
        }
    }
}
