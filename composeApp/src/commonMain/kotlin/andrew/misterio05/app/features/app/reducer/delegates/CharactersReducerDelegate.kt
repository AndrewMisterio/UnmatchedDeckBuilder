package andrew.misterio05.app.features.app.reducer.delegates

import andrew.misterio05.app.features.Effect
import andrew.misterio05.app.features.ReducerResult
import andrew.misterio05.app.features.app.AppState
import andrew.misterio05.app.features.details.DetailsState
import andrew.misterio05.app.features.categories.CategoriesEvent
import andrew.misterio05.app.features.categories.CategoriesReducer
import andrew.misterio05.app.features.categories.CategoriesState
import andrew.misterio05.app.features.just
import andrew.misterio05.app.features.main.MainEvent
import andrew.misterio05.app.features.with
import arrow.core.partially2

fun AppState.onCharactersEvent(event: CategoriesEvent): ReducerResult<AppState> = when (event) {
    is CategoriesEvent.CreateNewCategory -> {
        this.with(Effect.nextEvent(MainEvent.NewDetails(DetailsState.new(event.id))))
    }

    is CategoriesEvent.Internal -> {
        onScreen<CategoriesState>(CategoriesReducer::invoke.partially2(event))
    }

    else -> {
        just()
    }
}
