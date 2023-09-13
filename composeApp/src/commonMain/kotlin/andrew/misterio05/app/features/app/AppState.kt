package andrew.misterio05.app.features.app

import andrew.misterio05.app.features.State

data class AppState(
    val navigation: Navigation = Navigation(),
) : State {
    data class Navigation(
        val list: State? = null,
        val details: State? = null,
        val dialog: State? = null,
    ) {
        val current get() = details ?: list
    }
}
