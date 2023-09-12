package andrew.misterio05.app.features.characters

import andrew.misterio05.app.features.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class CharactersState(
    val items: ImmutableList<Item> = persistentListOf(),
    val isLoading: Boolean = true,
) : State {

    data class Item(
        val id: String,
        val title: String,
        val iconUrl: String,
    )
}
