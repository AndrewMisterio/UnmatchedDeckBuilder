package andrew.misterio05.app.features.categories

import andrew.misterio05.app.features.Reducer
import andrew.misterio05.app.features.just
import kotlinx.collections.immutable.toPersistentList

val CategoriesReducer = Reducer<CategoriesState, CategoriesEvent.Internal> { state, event ->
    when (event) {
        is CategoriesEvent.OnListLoaded -> {
            state.copy(
                items = event.list.toPersistentList(),
                isLoading = false
            )
                .just()
        }
    }
}
