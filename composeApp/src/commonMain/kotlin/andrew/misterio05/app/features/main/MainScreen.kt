@file:OptIn(ExperimentalAnimationApi::class)

package andrew.misterio05.app.features.main

import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.State
import andrew.misterio05.app.features.app.AppEvent
import andrew.misterio05.app.features.details.DetailsScreen
import andrew.misterio05.app.features.details.DetailsState
import andrew.misterio05.app.features.categories.CategoriesState
import andrew.misterio05.app.features.categories.CategoriesScreen
import andrew.misterio05.app.features.projects.ProjectsScreen
import andrew.misterio05.app.features.projects.ProjectsState
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import arrow.optics.copy

@Composable
fun MainScreen(
    modifier: Modifier,
    mainState: MainState,
    dispatch: (Event) -> Unit,
) = Box(modifier) {
    val screenModifier = Modifier.fillMaxSize()
    val state = mainState.current
    updateTransition(targetState = state, label = "MainScreen").AnimatedContent(
        modifier = Modifier.fillMaxSize(),
        contentKey = { if (it != null) it::class else null },
        content = { if (it != null) RenderScreen(screenModifier, it, dispatch) }
    )
    if (mainState.dialog != null) Dialog(
        onCloseRequest = remember { { dispatch(AppEvent.CloseDialog) } },
        content = { RenderScreen(screenModifier.copy {}, mainState.dialog, dispatch) },
    )
}

@Composable
private fun RenderScreen(
    modifier: Modifier,
    screen: State,
    dispatch: (Event) -> Unit
) {
    when (screen) {
        is ProjectsState -> ProjectsScreen(modifier, screen, dispatch)
        is CategoriesState -> CategoriesScreen(modifier, screen, dispatch)
        is DetailsState -> DetailsScreen(modifier, screen, dispatch)
    }
}
