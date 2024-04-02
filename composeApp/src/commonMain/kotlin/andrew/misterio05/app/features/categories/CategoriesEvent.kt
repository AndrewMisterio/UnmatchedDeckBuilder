package andrew.misterio05.app.features.categories

import andrew.misterio05.app.features.Event

sealed interface CategoriesEvent: Event {
    data class OpenDetails(val id: String): CategoriesEvent
    data class CreateNewCategory(val id: String): CategoriesEvent
    data class OnListLoaded(val list: List<CategoriesState.Item>) : Internal

    sealed interface Internal: CategoriesEvent
}
