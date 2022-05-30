package com.example.myapplication.data

import com.example.myapplication.data.db.MovieDao
import com.example.myapplication.model.Movie


class MovieLocalDataSource(val movieDao:MovieDao?) {

    fun insertMovie(movie: Movie)
    {
        movieDao?.insert(movie)
    }
}
