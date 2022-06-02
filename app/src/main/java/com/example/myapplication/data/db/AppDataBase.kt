package com.example.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Movie


@Database(entities = [Movie::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao?

    companion object {
        const val DATABASE_NAME: String = "myDB"
    }
}