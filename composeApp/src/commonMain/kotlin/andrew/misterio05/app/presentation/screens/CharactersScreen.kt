package andrew.misterio05.app.presentation.screens

import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.characters.CharactersEvent
import andrew.misterio05.app.features.characters.CharactersState
import andrew.misterio05.app.presentation.CARD_RATIO
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier,
    state: CharactersState,
    dispatch: (Event) -> Unit,
) = Box(
    modifier = modifier
        .padding(16.dp),
) {
    if (state.items.isNotEmpty()) {
        var isWidth by remember { mutableStateOf(false) }
        val gridCells by remember {
            derivedStateOf {
                if (isWidth) GridCells.Adaptive(100.dp)
                else GridCells.Fixed(2)
            }
        }
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { isWidth = it.width * 2 > it.height },
            columns = gridCells,
        ) {
            items(state.items) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(CARD_RATIO),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                    onClick = { dispatch(CharactersEvent.OpenDetails(it.title)) },
                ) {
                    Image(
                        painter = rememberImagePainter(url = it.iconUrl),
                        contentDescription = it.title,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }

    FloatingActionButton(
        modifier = Modifier.align(Alignment.BottomEnd),
        onClick = remember { { dispatch(CharactersEvent.CreateNewCharacter) } },
        content = { Icon(Icons.Filled.Add, "Create new character") },
    )

    AnimatedVisibility(state.isLoading) {
        Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            CircularProgressIndicator(
                modifier = Modifier.size(56.dp).align(Alignment.Center),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
