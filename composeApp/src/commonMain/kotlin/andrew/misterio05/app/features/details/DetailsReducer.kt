package andrew.misterio05.app.features.details

import andrew.misterio05.app.features.Reducer
import andrew.misterio05.app.features.just

val DetailsReducer = Reducer<DetailsState, DetailsEvent> { state, event ->
    state.just()
}
