package andrew.misterio05.app.features.app

import andrew.misterio05.app.features.State
import andrew.misterio05.app.features.main.MainState
import arrow.optics.optics

@optics
data class AppState(
    val main: MainState = MainState(),
) : State {
    companion object
}
