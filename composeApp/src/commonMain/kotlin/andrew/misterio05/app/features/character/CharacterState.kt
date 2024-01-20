package andrew.misterio05.app.features.character

import andrew.misterio05.app.features.State
import arrow.optics.optics
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@optics
data class CharacterState(
    val title: String,
    val cards: ImmutableList<Card>,
) : State {
    @optics
    data class Card(
        val name: String,
        val character: String,
        val type: Type,
        val value: Int,
        val basicDescription: String,
        val immediateDescription: String,
        val combatDescription: String,
        val afterEffectDescription: String,
        val count: Int,
        val boost: Int,
        val image: String,
    ) {
        enum class Type {
            ATK, VER, DEF, SCH;
        }

        companion object
    }

    companion object {
        fun new() = CharacterState(
            title = "Kakashi",
            cards = Card.Type.values().map(::makeExampleCard).toPersistentList(),
        )

        private fun makeExampleCard(type: Card.Type) = Card(
            name = "Raikiri",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzhLdzVnI1H68aQnXPOgqIBGdjhi_-ldCjPa5CqcZZcW0NH1bQbxgb8h2UWi6hsmIfXrI&usqp=CAU",
            character = "KAKASHI",
            type = type,
            value = 4,
            basicDescription = "You can play this card",
            immediateDescription = "The opposing fighter may not leave their space for the rest of the turn.",
            combatDescription = "If your fighter started this turn in a different space",
            afterEffectDescription = "If you won opponent discard 1 card.",
            boost = 2,
            count = 3,
        )
    }
}
