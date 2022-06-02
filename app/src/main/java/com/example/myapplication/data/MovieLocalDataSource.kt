package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.db.AppDataBase
import com.example.myapplication.model.Movie
import javax.inject.Inject


class MovieLocalDataSource @Inject constructor(val db:AppDataBase) {
    val allMovies: LiveData<List<Movie?>?>?
    val allUpComingMovies: LiveData<List<Movie?>?>?
    var countMovies: Int

    init
    {
        allMovies =db.movieDao()?.getAllMovie()
        allUpComingMovies =db.movieDao()?.getAllUpComingMovies()
        countMovies = db.movieDao()?.getCount() ?: 0
    }
    suspend fun insertMovie(movie: Movie)
    {
        db.movieDao()?.insert(movie)
    }
}
