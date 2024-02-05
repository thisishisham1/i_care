import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.icare.core.theme.neutralWhite
import com.example.icare.core.theme.green500

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = green500,
    contentColor: Color = neutralWhite,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 23.sp)
) {
    Button(
        onClick = onClick, shape = Shapes().medium, colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, contentColor = contentColor
        ), modifier = modifier
    ) {
        Text(text = text, style = textStyle)
    }
}