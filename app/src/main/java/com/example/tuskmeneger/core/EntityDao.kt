package com.example.tuskmeneger.core


import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Update
import com.example.tuskmeneger.logic.Task


@Dao
interface EntityDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun SetTask(taskEntity: TaskEntity): Long

    @Query("SELECT * FROM task")
    suspend fun GetAllTask(): List<TaskEntity>

    @Query("DELETE FROM task")
    suspend fun clearAllTasks()

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("SELECT * FROM task ORDER BY id ASC LIMIT 1 OFFSET :index")
    suspend fun getTaskInDao(index: Int): TaskEntity

}