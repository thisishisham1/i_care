import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun NewButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick, shape = RoundedCornerShape(20.dp)) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}