package andrew.misterio05.app.features.characters

import andrew.misterio05.app.features.Reducer
import andrew.misterio05.app.features.just
import andrew.misterio05.app.features.with
import kotlinx.collections.immutable.toPersistentList

val CharactersReducer = Reducer<CharactersState, CharactersEvent> { state, event ->
    when (event) {
        is CharactersEvent.OnListLoaded -> {
            state.copy(
                items = event.list.toPersistentList(),
                isLoading = false
            )
                .just()
        }

        is CharactersEvent.CreateNewCharacter -> {
            state.just()
        }

        is CharactersEvent.OpenDetails -> {
            state.with(CharactersEffect.LoadList)
        }
    }
}
