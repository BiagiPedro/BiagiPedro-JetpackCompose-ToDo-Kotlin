package com.example.todolist.domain

import java.sql.Time

data class Todo(
    val id: Long,
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val startTime: String,
    val endTime: String

)

// fake objects
val todo1 = Todo(
    id = 1,
    title = "Todo 1",
    description = "Description for Todo 1",
    isCompleted = false,
    startTime = "10:00",
    endTime = "12:00"
)

val todo2 = Todo(
    id = 2,
    title = "Todo 2",
    description = "Description for Todo 2",
    isCompleted = true,
    startTime = "14:00",
    endTime = "16:00"
)

val todo3 = Todo(
    id = 3,
    title = "Todo 3",
    description = "Description for Todo 3",
    isCompleted = false,
    startTime = "09:00",
    endTime = "11:00"
)
