package com.example.tuskmeneger.core

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.example.tuskmeneger.repository.TuskObject

@Dao
interface EntityDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun SetTusk(tuskObject: TuskObject): Long

    @Query("SELECT * FROM tusk")
    suspend fun GetAllTusk(): List<TuskObject>



}