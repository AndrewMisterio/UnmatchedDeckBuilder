package andrew.misterio05.app.features.categories

import andrew.misterio05.app.features.State
import arrow.optics.optics
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@optics
data class CategoriesState(
    val items: ImmutableList<Item> = persistentListOf(),
    val isLoading: Boolean = true,
) : State {

    @optics
    data class Item(
        val id: String,
        val title: String,
        val iconUrl: String,
    )

    companion object {
        fun new(project: String) = CategoriesState()
    }
}
