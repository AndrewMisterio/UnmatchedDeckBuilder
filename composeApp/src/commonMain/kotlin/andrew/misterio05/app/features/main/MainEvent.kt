package andrew.misterio05.app.features.main

import andrew.misterio05.app.features.Event
import andrew.misterio05.app.features.State

sealed interface MainEvent : Event {
    data class NewDetails(val state: State) : MainEvent

    data object Back: MainEvent
    data object CloseDialog: MainEvent
}
