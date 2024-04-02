package andrew.misterio05.app.features.app.reducer

import andrew.misterio05.app.features.Effect
import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.Reducer
import andrew.misterio05.app.features.app.AppEvent
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.app.reducer.delegates.onCharacterEvent
import andrew.misterio05.app.features.app.reducer.delegates.onCharactersEvent
import andrew.misterio05.app.features.app.reducer.delegates.onMainEvent
import andrew.misterio05.app.features.details.DetailsEvent
import andrew.misterio05.app.features.categories.CategoriesEffect
import andrew.misterio05.app.features.categories.CategoriesEvent
import andrew.misterio05.app.features.just
import andrew.misterio05.app.features.main.MainEvent
import andrew.misterio05.app.features.with

val AppReducer = Reducer<AppState, Event> { state, event ->
    when (event) {
        is AppEvent.OnAppStarted -> state.with(CategoriesEffect.LoadList)
        is AppEvent.CloseDialog -> state.with(Effect.nextEvent(MainEvent.CloseDialog))
        is MainEvent -> state.onMainEvent(event)
        is CategoriesEvent -> state.onCharactersEvent(event)
        is DetailsEvent -> state.onCharacterEvent(event)
        else -> state.just()
    }
}
