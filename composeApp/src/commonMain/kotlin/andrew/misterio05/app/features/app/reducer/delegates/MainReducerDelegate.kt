package andrew.misterio05.app.features.app.reducer.delegates

import andrew.misterio05.app.features.ReducerResult
import andrew.misterio05.app.features.State
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.just
import andrew.misterio05.app.features.main.MainEvent
import andrew.misterio05.app.features.main.MainReducer
import andrew.misterio05.app.features.main.MainState
import andrew.misterio05.app.features.with

fun AppState.onMainEvent(event: MainEvent): ReducerResult<AppState> {
    val mainResult = MainReducer(main, event)
    return copy(main = mainResult.state).with(mainResult.effects)
}

inline fun <reified T: State> AppState.onScreen(reduce: (T) -> ReducerResult<T>): ReducerResult<AppState> {
    val mainReduceResult: ReducerResult<MainState>? =  when {
        main.list is T -> reduce(main.list).let { main.copy(list = it.state).with(it.effects) }
        main.details is T -> reduce(main.details).let { main.copy(details = it.state).with(it.effects) }
        main.dialog is T -> reduce(main.dialog).let { main.copy(dialog = it.state).with(it.effects) }
        else -> null
    }

    return when(mainReduceResult) {
        null -> this.just()
        else -> copy(main = mainReduceResult.state).with(mainReduceResult.effects)
    }
}
