package com.example.tuskmeneger.core

import android.app.Application
import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room3.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper


@Database(
    version = 1,
    entities = [TuskEntity::class],
    exportSchema = false
)


@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun TuskDao() : EntityDao

    companion object {
    @Volatile
    private var INSTANCE: AppDataBase? = null


        fun GetInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "appDb"
                )
                    .addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db : SupportSQLiteDatabase){
                            super.onCreate(db)
                        }
                    }
                ).build()
                    .also { INSTANCE = it }
            }
        }

    }
}