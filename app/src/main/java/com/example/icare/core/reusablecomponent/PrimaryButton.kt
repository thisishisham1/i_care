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
import com.example.icare.core.theme.green500
import com.example.icare.core.theme.neutralWhite

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = green500,
    contentColor: Color = neutralWhite,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(
            65.dp
        ),
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 23.sp)
) {
    Button(
        onClick = onClick, shape = Shapes().medium, colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, contentColor = contentColor
        ), modifier = modifier, enabled = isEnabled
    ) {
        Text(text = text, style = textStyle)
    }
}