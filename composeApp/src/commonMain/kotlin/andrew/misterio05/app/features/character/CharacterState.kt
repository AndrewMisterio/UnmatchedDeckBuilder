package andrew.misterio05.app.features.character

import andrew.misterio05.app.features.State
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class CharacterState(
    val title: String,
    val cards: ImmutableList<Card>,
) : State {
    data class Card(
        val name: String,
    )

    companion object {
        fun new() = CharacterState(
            title = "",
            cards = persistentListOf(),
        )
    }
}
