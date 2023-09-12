package andrew.misterio05.app.features.characters

import andrew.misterio05.app.features.EffHandler
import andrew.misterio05.app.features.Event
import kotlinx.coroutines.CoroutineScope
import java.util.UUID

class CharactersEffectHandler : EffHandler<CharactersEffect> {
    override suspend fun CoroutineScope.execute(eff: CharactersEffect, dispatch: (Event) -> Unit) {
        dispatch(
            CharactersEvent.OnListLoaded(
                listOf(
                    CharactersState.Item(
                        id = UUID.randomUUID().toString(),
                        title = "Hatake Kakashi",
                        iconUrl = "https://w0.peakpx.com/wallpaper/458/866/HD-wallpaper-kakashi-kakashi-sensei.jpg",
                    ),
                )
            )
        )
    }
}
