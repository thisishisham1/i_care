import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NewButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick, shape = Shapes().medium) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
    }
}