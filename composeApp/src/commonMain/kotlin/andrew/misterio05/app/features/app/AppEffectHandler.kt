package andrew.misterio05.app.features.app

import andrew.misterio05.app.features.EffHandler
import andrew.misterio05.app.features.EffHandler.Companion.execute
import andrew.misterio05.app.features.Effect
import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.characters.CharactersEffect
import andrew.misterio05.app.features.characters.CharactersEffectHandler
import kotlinx.coroutines.CoroutineScope

class AppEffectHandler(
    private val charactersEffectHandler: CharactersEffectHandler,
) : EffHandler<Effect> {
    override suspend fun CoroutineScope.execute(eff: Effect, dispatch: (Event) -> Unit) {
        when (eff) {
            is CharactersEffect -> charactersEffectHandler.execute(this, eff, dispatch)
        }
    }
}
