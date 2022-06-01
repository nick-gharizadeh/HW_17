package com.example.myapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.Movie


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(movie: Movie?)

    @Query("SELECT * from movie where isUpComing=0 ORDER By id ASC ")
    fun getAllMovie(): LiveData<List<Movie?>?>?

    @Query("SELECT * from movie where isUpComing=1 ORDER By id ASC ")
    fun getAllUpComingMovies(): LiveData<List<Movie?>?>?

    @Query("SELECT COUNT(*) from movie")
    fun getCount(): Int

}