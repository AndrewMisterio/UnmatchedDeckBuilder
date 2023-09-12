package andrew.misterio05.app.features.app

import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.Reducer
import andrew.misterio05.app.features.characters.CharactersEffect
import andrew.misterio05.app.features.characters.CharactersEvent
import andrew.misterio05.app.features.characters.CharactersReducer
import andrew.misterio05.app.features.characters.CharactersState
import andrew.misterio05.app.features.just
import andrew.misterio05.app.features.with

val AppReducer = Reducer<AppState, Event> { state, event ->
    when (event) {
        is AppEvent.OnAppStarted -> {
            state.with(CharactersEffect.LoadList)
        }

        is CharactersEvent -> {
            (state.navigation.list as? CharactersState)
                ?.let { charactersState ->
                    val reducerResult = CharactersReducer(charactersState, event)
                    state.copy(
                        navigation = state.navigation.copy(
                            list = reducerResult.state,
                        )
                    )
                        .with(reducerResult.effects)
                }
                ?: state.just()
        }

        else -> {
            state.just()
        }
    }
}
