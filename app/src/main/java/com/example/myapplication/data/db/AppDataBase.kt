package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.Movie


//@Database(entities = [Movie::class], version = 1)
//abstract class AppDataBase : RoomDatabase() {
//    abstract fun movieDao(): MovieDao?
//
//    companion object {
//        private var INSTANCE: AppDataBase? = null
//        fun getDatabase(context: Context): AppDataBase? {
//            if (INSTANCE == null) {
//                synchronized(AppDataBase::class.java) {
//                    if (INSTANCE == null) {
//                        INSTANCE = Room.databaseBuilder(
//                            context.getApplicationContext(),
//                            AppDataBase::class.java, "movie_database"
//                        ).allowMainThreadQueries()
//                            .build()
//                    }
//                }
//            }
//            return INSTANCE
//        }
//    }
//}