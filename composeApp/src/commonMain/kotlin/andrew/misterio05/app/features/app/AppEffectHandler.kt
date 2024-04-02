package andrew.misterio05.app.features.app

import andrew.misterio05.app.features.EffHandler
import andrew.misterio05.app.features.EffHandler.Companion.execute
import andrew.misterio05.app.features.Effect
import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.categories.CategoriesEffect
import andrew.misterio05.app.features.categories.CategoriesEffectHandler
import kotlinx.coroutines.CoroutineScope

class AppEffectHandler(
    private val categoriesEffectHandler: CategoriesEffectHandler,
) : EffHandler<Effect> {
    override suspend fun CoroutineScope.execute(eff: Effect, dispatch: (Event) -> Unit) {
        when (eff) {
            is CategoriesEffect -> categoriesEffectHandler.execute(this, eff, dispatch)
        }
    }
}
