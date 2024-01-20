package andrew.misterio05.app.features.app.reducer.delegates

import andrew.misterio05.app.features.ReducerResult
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.character.CharacterEvent
import andrew.misterio05.app.features.character.CharacterReducer
import andrew.misterio05.app.features.character.CharacterState
import arrow.core.partially2

fun AppState.onCharacterEvent(event: CharacterEvent): ReducerResult<AppState> {
    return onScreen<CharacterState>(CharacterReducer::invoke.partially2(event))
}
