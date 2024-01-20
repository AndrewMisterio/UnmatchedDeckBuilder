package andrew.misterio05.app.features.character

import andrew.misterio05.app.features.Reducer
import andrew.misterio05.app.features.just

val CharacterReducer = Reducer<CharacterState, CharacterEvent> { state, event ->
    state.just()
}
