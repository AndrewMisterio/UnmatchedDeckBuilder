package andrew.misterio05.app.features.projects

import andrew.misterio05.app.features.State

data class ProjectsState(
    val projects: List<String>,
) : State {
    companion object {
        fun new() = ProjectsState(emptyList())
    }
}
