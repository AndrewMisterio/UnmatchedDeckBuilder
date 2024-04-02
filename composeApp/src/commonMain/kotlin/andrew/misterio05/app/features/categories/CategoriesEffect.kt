package andrew.misterio05.app.features.categories

import andrew.misterio05.app.features.Effect

sealed interface CategoriesEffect : Effect {
    data object LoadList : CategoriesEffect
}
