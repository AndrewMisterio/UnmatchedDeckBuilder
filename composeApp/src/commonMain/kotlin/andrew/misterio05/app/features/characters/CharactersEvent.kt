package andrew.misterio05.app.features.characters

import andrew.misterio05.app.features.Event

sealed interface CharactersEvent: Event {
    data class OpenDetails(val id: String): CharactersEvent
    data object CreateNewCharacter: CharactersEvent
    data class OnListLoaded(val list: List<CharactersState.Item>) : CharactersEvent
}
