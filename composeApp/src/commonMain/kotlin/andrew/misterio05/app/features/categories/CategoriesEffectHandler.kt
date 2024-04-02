package andrew.misterio05.app.features.categories

import andrew.misterio05.app.features.EffHandler
import andrew.misterio05.app.features.Event
import kotlinx.coroutines.CoroutineScope
import java.util.UUID

class CategoriesEffectHandler : EffHandler<CategoriesEffect> {
    override suspend fun CoroutineScope.execute(eff: CategoriesEffect, dispatch: (Event) -> Unit) {
        dispatch(
            CategoriesEvent.OnListLoaded(
                listOf(
                    CategoriesState.Item(
                        id = UUID.randomUUID().toString(),
                        title = "Hatake Kakashi",
                        iconUrl = "https://w0.peakpx.com/wallpaper/458/866/HD-wallpaper-kakashi-kakashi-sensei.jpg",
                    ),
                )
            )
        )
    }
}
