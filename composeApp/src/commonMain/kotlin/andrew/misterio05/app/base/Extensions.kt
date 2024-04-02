package andrew.misterio05.app.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Suppress("ComposableNaming")
@Composable
fun <SIDE_EFFECT : Any> Flow<SIDE_EFFECT>.onEvent(
    sideEffect: (suspend (sideEffect: SIDE_EFFECT) -> Unit)
) {
    val sideEffectFlow = this

    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect { sideEffect(it) }
    }
}
