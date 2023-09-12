package andrew.misterio05.app.features.characters

import andrew.misterio05.app.features.Effect

sealed interface CharactersEffect : Effect {
    data object LoadList : CharactersEffect
}
