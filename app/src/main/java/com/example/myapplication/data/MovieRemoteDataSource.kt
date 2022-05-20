package com.example.myapplication.data

import com.example.myapplication.data.network.MovieApi
import com.example.myapplication.model.Movie

class MovieRemoteDataSource {

    suspend fun getMovie():List<Movie>{
        return MovieApi.retrofitService.getMovies().results
    }


}