package com.example.tuskmeneger.core




import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.room3.ColumnTypeConverter
import androidx.room3.ColumnTypeConverters
import androidx.sqlite.SQLiteConnection





@Database(
    version = 1,
    entities = [TaskEntity::class],
    exportSchema = false
)


@ColumnTypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun TaskDao() : EntityDao

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
                        override suspend fun onCreate(db : SQLiteConnection){
                            super.onCreate(db)
                        }
                    }
                ).build()
                    .also { INSTANCE = it }
            }
        }

    }
}