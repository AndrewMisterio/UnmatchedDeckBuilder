package andrew.misterio05.app.base.views

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class, ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    modifier: Modifier,
    text: String,
    label: String? = null,
    isSingleLine: Boolean = false,
    onTextChanged: (String) -> Unit,
) {
    var value by remember { mutableStateOf(text) }

    OutlinedTextField(
        modifier = modifier,
        label = label?.let { { Text(text = it) } },
        value = value,
        singleLine = isSingleLine,
        onValueChange = { value = it },
    )

    LaunchedEffect(onTextChanged) {
        snapshotFlow { value }
            .debounce(200.milliseconds)
            .collect(onTextChanged)
    }
}