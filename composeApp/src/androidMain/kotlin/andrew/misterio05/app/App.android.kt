package andrew.misterio05.app

import andrew.misterio05.app.features.app.AppEffectHandler
import andrew.misterio05.app.features.characters.CharactersEffectHandler
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.option.androidContext
import okio.Path.Companion.toOkioPath

class AndroidApp : Application() {

    internal val appEffectHandler = AppEffectHandler(
        charactersEffectHandler = CharactersEffectHandler(),
    )
}

class AppActivity : ComponentActivity() {

    private val app = applicationContext as AndroidApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalImageLoader provides remember(::generateImageLoader),
                content = { App(app.appEffectHandler) },
            )
        }
    }
}

private fun Context.generateImageLoader(): ImageLoader {
    return ImageLoader {
        options {
            androidContext(applicationContext)
        }
        components {
            setupDefaultComponents()
        }
        interceptor {
            memoryCacheConfig {
                // Set the max size to 25% of the app's available memory.
                maxSizePercent(this@generateImageLoader, 0.25)
            }
            diskCacheConfig {
                directory(cacheDir.resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}

@SuppressLint("DiscouragedApi")
internal actual fun font(res: String, weight: FontWeight, style: FontStyle): Font {
    val resources = Resources.getSystem()
    val id = resources.getIdentifier(res, "font", resources.getString(R.string.app_id))
    return Font(id, weight, style)
}
