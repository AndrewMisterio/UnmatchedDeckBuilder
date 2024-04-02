package andrew.misterio05.app.features.main

import andrew.misterio05.app.features.State
import arrow.optics.optics

@optics
data class MainState(
    val projects: State? = null,
    val categories: State? = null,
    val details: State? = null,
    val dialog: State? = null,
) : State {
    val current get() = details ?: categories ?: projects

    companion object {
        fun new() = MainState()
    }
}
