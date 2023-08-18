import androidx.compose.ui.window.ComposeUIViewController
import andrew.misterio05.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
