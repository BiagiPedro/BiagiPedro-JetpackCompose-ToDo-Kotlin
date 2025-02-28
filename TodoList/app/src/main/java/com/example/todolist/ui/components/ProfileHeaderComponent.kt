import com.example.todolist.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.Orange

@Composable
@Preview
fun ProfileHeaderComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        )

        Box(modifier = Modifier.padding(end = 16.dp)) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier.size(24.dp)
            )
            Box(
                modifier = Modifier
                    .size(10.dp) // Define o tamanho da bolinha
                    .background(Orange, shape = CircleShape) // Torna a bolinha laranja e circular
                    .align(Alignment.TopEnd) // Posiciona no canto superior direito
            )
        }


    }
}