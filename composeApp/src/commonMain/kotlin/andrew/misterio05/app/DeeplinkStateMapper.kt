package andrew.misterio05.app

import andrew.misterio05.app.features.categories.CategoriesState
import andrew.misterio05.app.features.details.DetailsState
import andrew.misterio05.app.features.main.MainState
import andrew.misterio05.app.features.projects.ProjectsState

object DeeplinkStateMapper {

    fun link(value: String?): MainState {
        val fragments = value?.split("/").orEmpty()
        val project = fragments.getOrNull(0)
        val category = fragments.getOrNull(1)
        return MainState(
            projects = ProjectsState.new(),
            categories = project?.let(CategoriesState::new),
            details = category?.takeIf { project != null }?.let(DetailsState::new),
        )
    }
}
