import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge.copy(fontSize = 18.sp)
) {
    Button(
        onClick = onClick, shape = Shapes().medium, colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, contentColor = contentColor
        ), modifier = modifier, enabled = isEnabled
    ) {
        Text(text = text, style = textStyle)
    }
}