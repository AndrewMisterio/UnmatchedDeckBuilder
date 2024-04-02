import andrew.misterio05.app.App
import andrew.misterio05.app.features.app.AppEffectHandler
import andrew.misterio05.app.features.categories.CategoriesEffectHandler
import andrew.misterio05.app.generateImageLoader
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.seiko.imageloader.LocalImageLoader

internal const val ApplicationName = "UnmatchedDeckBuilder"

fun main(vararg args: String) {
    val appEffectHandler = AppEffectHandler(
        categoriesEffectHandler = CategoriesEffectHandler(),
    )
    application {
        Window(
            title = ApplicationName,
            state = rememberWindowState(width = 800.dp, height = 600.dp),
            onCloseRequest = ::exitApplication,
            content = {
                CompositionLocalProvider(
                    LocalImageLoader provides remember(::generateImageLoader),
                    content = {
                        App(
                            effectHandler = appEffectHandler,
                            deeplink = args.getOrNull(0),
                        )
                    },
                )
            },
        )
    }
}
