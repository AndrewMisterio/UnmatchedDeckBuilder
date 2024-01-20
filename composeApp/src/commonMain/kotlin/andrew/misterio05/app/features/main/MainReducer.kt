package andrew.misterio05.app.features.main

import andrew.misterio05.app.features.Reducer
import andrew.misterio05.app.features.just

val MainReducer = Reducer<MainState, MainEvent> { state, event ->
    state.just()
}