package com.example.flowers.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [(Flower::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun FlowerDAO(): FlowerDAO

    companion object
    {
        private var INSTANCE: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "flowers.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE !!
        }

        fun getInstance() : AppDatabase {
            if(INSTANCE == null)
                throw RuntimeException("Database should be initialized at this point. Use getInstance(context : Context) first ")
            return INSTANCE !!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
