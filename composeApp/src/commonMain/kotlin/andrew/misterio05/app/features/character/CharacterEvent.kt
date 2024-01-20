package andrew.misterio05.app.features.character

sealed interface CharacterEvent {
    data class OnCardClicked(val id: String): CharacterEvent
}
