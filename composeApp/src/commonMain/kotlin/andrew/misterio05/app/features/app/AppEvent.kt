package andrew.misterio05.app.features.app

import andrew.misterio05.app.features.Event

sealed interface AppEvent: Event {
    data object OnAppStarted: AppEvent
}
