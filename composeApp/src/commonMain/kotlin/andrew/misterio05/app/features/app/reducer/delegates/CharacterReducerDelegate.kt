package andrew.misterio05.app.features.app.reducer.delegates

import andrew.misterio05.app.features.ReducerResult
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.details.DetailsEvent
import andrew.misterio05.app.features.details.DetailsReducer
import andrew.misterio05.app.features.details.DetailsState
import arrow.core.partially2

fun AppState.onCharacterEvent(event: DetailsEvent): ReducerResult<AppState> {
    return onScreen<DetailsState>(DetailsReducer::invoke.partially2(event))
}
