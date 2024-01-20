package andrew.misterio05.app.features.app.reducer.delegates

import andrew.misterio05.app.features.Effect
import andrew.misterio05.app.features.ReducerResult
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.character.CharacterState
import andrew.misterio05.app.features.characters.CharactersEvent
import andrew.misterio05.app.features.characters.CharactersReducer
import andrew.misterio05.app.features.characters.CharactersState
import andrew.misterio05.app.features.just
import andrew.misterio05.app.features.main.MainEvent
import andrew.misterio05.app.features.with
import arrow.core.partially2

fun AppState.onCharactersEvent(event: CharactersEvent): ReducerResult<AppState> = when (event) {
    is CharactersEvent.CreateNewCharacter -> {
        this.with(Effect.nextEvent(MainEvent.NewDetails(CharacterState.new())))
    }

    is CharactersEvent.Internal -> {
        onScreen<CharactersState>(CharactersReducer::invoke.partially2(event))
    }

    else -> {
        just()
    }
}
