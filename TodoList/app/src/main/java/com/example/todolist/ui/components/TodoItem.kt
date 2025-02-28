package com.example.todolist.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.R
import com.example.todolist.domain.Todo
import com.example.todolist.ui.theme.LightBlue
import com.example.todolist.ui.theme.LightGreen
import com.example.todolist.ui.theme.LightPurple

@Composable
fun TodoItem(
    todo: Todo,
    onCompletedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    val taskColor = listOf(LightPurple, LightGreen, LightBlue).random()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Exibe o horário de início da tarefa
        Text(
                text = "${todo.startTime}\nHoras",  // Exibe o horário de início
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            textAlign = TextAlign.Center
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
        /*
        - ChatGPT
        Modifique essa parte do Box para torna la circular e clicavel, use a variavel isCompleted como parametro.
         */
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(if (todo.isCompleted) Color.Black else Color.Transparent)
                .clickable { onCompletedChange(!todo.isCompleted) }
                .border(
                    border = BorderStroke(3.dp, Color.Black),
                    shape = CircleShape
                )
        )

        Divider(modifier = Modifier.width(6.dp), color = Color.Black)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Container para a descrição da tarefa
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(taskColor)
                    .weight(0.9f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Título da tarefa
                Text(
                    text = todo.title,
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    modifier = Modifier.padding(
                        start = 12.dp,
                        top = 12.dp
                    )
                )

                // Descrição da tarefa (se existir)
                if (todo.description != null) {
                    Text(
                        text = todo.description,
                        fontFamily = FontFamily(Font(R.font.nunito_bold)),
                        modifier = Modifier.padding(start = 12.dp),
                        color = Color.Gray
                    )
                }

                // Exibe horário de início e fim da tarefa
                Text(
                    text = "${todo.startTime} - ${todo.endTime}",
                    fontFamily = FontFamily(Font(R.font.nunito_bold)),
                    modifier = Modifier.padding(start = 12.dp),
                    color = Color.Black
                )

                // Botão para deletar tarefa
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onDeleteClick,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Black
                        )
                    }
                }
            }

            Divider(
                modifier = Modifier
                    .width(16.dp)
                    .weight(0.1f),
                color = Color.Black
            )
        }
        }
    }
}
