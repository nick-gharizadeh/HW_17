package com.example.myapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.Movie


@Dao
interface MovieDao {

    @Insert
    fun insert(movie: Movie?)

    @Query("SELECT * from movie ORDER BY id ASC")
    fun getAllWords(): LiveData<List<Movie?>?>?

    @Query("SELECT COUNT(*) from movie")
    fun getCount(): LiveData<Int>?

}