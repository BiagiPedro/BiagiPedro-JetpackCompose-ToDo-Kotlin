package com.example.todolist.data

import com.example.todolist.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
ChatGPT
Faca todas as modificacoes nescessarias para de adequar ao banco de dados na atual situaçao (startTime , endTime)
* */


class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override suspend fun insert(title: String, description: String?, id: Long?, startTime: String, endTime: String) {
        val entity = id?.let {
            dao.getBy(it)?.copy(
                title = title,
                description = description,
            )
        } ?: TodoEntity(
            title = title,
            description = description,
            isCompleted = false,
            startTime = startTime,
            endTime = endTime
        )

        dao.insert(entity)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        val existingEntity = dao.getBy(id) ?: return
        val updatedEntity = existingEntity.copy(isCompleted = isCompleted)
        dao.insert(updatedEntity)
    }

    override suspend fun delete(id: Long) {
        val existingEntity = dao.getBy(id) ?: return
        dao.delete(existingEntity)
    }

    override fun getAll(): Flow<List<Todo>> {
        return dao.getAll().map { entities ->
            entities.map { entity ->
                Todo(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    isCompleted = entity.isCompleted,
                    startTime = entity.startTime,
                    endTime = entity.endTime
                )
            }
        }
    }

    override suspend fun getBy(id: Long): Todo? {
        return dao.getBy(id)?.let { entity ->
            Todo(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                isCompleted = entity.isCompleted,
                startTime = entity.startTime,
                endTime = entity.endTime

            )
        }
    }
}