import andrew.misterio05.app.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("UnmatchedDeckBuilder") {
            App()
        }
    }
}
