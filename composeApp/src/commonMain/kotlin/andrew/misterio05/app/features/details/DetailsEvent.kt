package andrew.misterio05.app.features.details

sealed interface DetailsEvent {
    data class OnCardClicked(val id: String): DetailsEvent
}
