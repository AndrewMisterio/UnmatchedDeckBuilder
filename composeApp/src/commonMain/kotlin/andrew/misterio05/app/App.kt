package andrew.misterio05.app

import andrew.misterio05.app.features.StateHolder
import andrew.misterio05.app.features.app.AppEffectHandler
import andrew.misterio05.app.features.app.AppEvent
import andrew.misterio05.app.features.app.AppReducer
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.characters.CharactersState
import andrew.misterio05.app.presentation.screens.ListScreen
import andrew.misterio05.app.presentation.theme.AppTheme
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun App(
    effectHandler: AppEffectHandler,
) = AppTheme(useDarkTheme = true) {

    val stateHolder = remember {
        StateHolder(
            AppState(navigation = AppState.Navigation(list = CharactersState())),
            AppReducer::invoke,
        )
    }

    val state by stateHolder.state.collectAsState()

    updateTransition(targetState = state.navigation.current, label = "root").AnimatedContent(
        modifier = Modifier.fillMaxSize(),
        contentKey = { if (it != null) it::class else null },
    ) { screen ->
        when (screen) {
            is CharactersState -> ListScreen(Modifier, screen, stateHolder::dispatch)
        }
    }

    LaunchedEffect(stateHolder.events) {
        stateHolder.dispatch(AppEvent.OnAppStarted)
        stateHolder.events.collect { effect ->
            launch { effectHandler.run { execute(effect, stateHolder::dispatch) } }
        }
    }
}

internal expect fun font(res: String, weight: FontWeight, style: FontStyle): Font
