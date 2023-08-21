package andrew.misterio05.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.rememberImageAction
import com.seiko.imageloader.rememberImageActionPainter

@Composable
fun urlPainter(url: String): Painter {
    val request = remember(url) { ImageRequest { data(url);  } }

    val action by rememberImageAction(request)
    return rememberImageActionPainter(action)
}
