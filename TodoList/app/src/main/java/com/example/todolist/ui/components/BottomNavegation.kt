package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar(selectedScreen: Int, onScreenSelected: (Int) -> Unit) {
    val screens = listOf(
        Icons.Filled.CalendarMonth to "Calendar",
        Icons.Filled.Home to "Home",
        Icons.Filled.Mail to "Notifications"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEachIndexed { index, (icon, description) ->
                val isSelected = selectedScreen == index

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.Black else Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { onScreenSelected(index) }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = description,
                            tint = if (isSelected) Color.White else Color.Black
                        )
                    }
                }
            }
        }
    }
}
