package com.example.tuskmeneger.core


import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query


@Dao
interface EntityDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun SetTask(taskEntity: TaskEntity): Long

    @Query("SELECT * FROM task")
    suspend fun GetAllTask(): List<TaskEntity>



}