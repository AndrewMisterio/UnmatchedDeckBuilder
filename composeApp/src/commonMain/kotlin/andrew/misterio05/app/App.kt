package andrew.misterio05.app

import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.State
import andrew.misterio05.app.features.StateHolder
import andrew.misterio05.app.features.app.AppEffectHandler
import andrew.misterio05.app.features.app.AppEvent
import andrew.misterio05.app.features.app.reducer.AppReducer
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.character.CharacterState
import andrew.misterio05.app.features.characters.CharactersState
import andrew.misterio05.app.features.main.MainState
import andrew.misterio05.app.presentation.screens.CharacterScreen
import andrew.misterio05.app.presentation.screens.ListScreen
import andrew.misterio05.app.presentation.theme.AppTheme
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun App(
    effectHandler: AppEffectHandler,
) = AppTheme(useDarkTheme = true) {

    val stateHolder = remember {
        StateHolder(
            init = AppState(main = MainState(list = CharactersState(), details = CharacterState.new())),
            reducer = AppReducer::invoke,
        )
    }

    val state by stateHolder.state.collectAsState()

    updateTransition(targetState = state.main.current, label = "root").AnimatedContent(
        modifier = Modifier.fillMaxSize(),
        contentKey = { if (it != null) it::class else null },
    ) { screen ->
        val modifier = Modifier.fillMaxSize()
        // TODO Create screen container with own state and nav bar
        Screen(screen = screen, modifier = modifier, dispatch = stateHolder::dispatch)
    }

    state.main.dialog?.let { dialog ->
        Dialog(
            onCloseRequest = remember { { stateHolder.dispatch(AppEvent.CloseDialog) } },
        ) {
            Screen(
                screen = dialog,
                modifier = Modifier.wrapContentSize(),
                dispatch = stateHolder::dispatch,
            )
        }
    }

    LaunchedEffect(stateHolder.events) {
        stateHolder.dispatch(AppEvent.OnAppStarted)
        stateHolder.events.collect { effect ->
            launch { effectHandler.run { execute(effect, stateHolder::dispatch) } }
        }
    }
}

@Composable
private fun Screen(
    screen: State?,
    modifier: Modifier,
    dispatch: (Event) -> Unit,
) {
    when (screen) {
        is CharactersState -> ListScreen(modifier, screen, dispatch)
        is CharacterState -> CharacterScreen(modifier, screen, dispatch)
    }
}

internal expect fun font(res: String, weight: FontWeight, style: FontStyle): Font
