package com.example.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
-ChatGPT
Estou com problemas na hora de iniciar o app , ele abre mas não inicia , creio que o problema esteja na parte do Banco de Dados ,
tive que fazer algumas modificaçoes nele , colocando mais colunas no banco ,arrume.
*/
@Database(entities = [TodoEntity::class], version = 2, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo-app"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
