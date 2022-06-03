package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.data.db.MovieDao
import com.example.myapplication.model.Movie


class MovieLocalDataSource(val movieDao:MovieDao?) {
    val allMovies: LiveData<List<Movie?>?>?
    val allUpComingMovies: LiveData<List<Movie?>?>?
    var countMovies: Int

    init
    {
        allMovies =movieDao?.getAllMovie()
        allUpComingMovies =movieDao?.getAllUpComingMovies()
        countMovies = movieDao?.getCount() ?: 0
    }
    suspend fun insertMovie(movie: Movie)
    {
        movieDao?.insert(movie)
    }

   suspend fun searchLocalMovie(query: String): List<Movie>
    {
       return movieDao?.searchMovie(query)!!
    }

    suspend fun getMovieByID(id:Int): Movie
    {
       return movieDao?.getMovieByID(id)!!
    }
}
