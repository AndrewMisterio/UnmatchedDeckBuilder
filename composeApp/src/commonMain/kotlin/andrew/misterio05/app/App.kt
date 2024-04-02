package andrew.misterio05.app

import andrew.misterio05.app.base.theme.AppTheme
import andrew.misterio05.app.features.StateHolder
import andrew.misterio05.app.features.app.AppEffectHandler
import andrew.misterio05.app.features.app.AppEvent
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.app.reducer.AppReducer
import andrew.misterio05.app.features.details.DetailsState
import andrew.misterio05.app.features.categories.CategoriesState
import andrew.misterio05.app.features.main.MainScreen
import andrew.misterio05.app.features.main.MainState
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

@Composable
internal fun App(
    deeplink: String? = null,
    effectHandler: AppEffectHandler,
) = AppTheme(useDarkTheme = true) {
    println(deeplink)
    val stateHolder = remember {
        StateHolder(
            init = AppState(main = DeeplinkStateMapper.link(deeplink)),
            reducer = AppReducer::invoke,
        )
    }

    val state by stateHolder.state.collectAsState()

    MainScreen(
        modifier = Modifier.fillMaxSize(),
        mainState = state.main,
        dispatch = stateHolder::dispatch,
    )

    LaunchedEffect(stateHolder.events) {
        stateHolder.dispatch(AppEvent.OnAppStarted)
        stateHolder.events.collect { effect ->
            launch { effectHandler.run { execute(effect, stateHolder::dispatch) } }
        }
    }
}

internal expect fun font(res: String, weight: FontWeight, style: FontStyle): Font
