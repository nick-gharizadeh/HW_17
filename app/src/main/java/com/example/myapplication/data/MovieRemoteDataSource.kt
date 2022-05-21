package com.example.myapplication.data

import com.example.myapplication.data.network.MovieApi
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetail

class MovieRemoteDataSource {

    suspend fun getMovie():List<Movie>{
        return MovieApi.retrofitService.getMovies().results
    }
    suspend fun getUpComingMovies():List<Movie>{
        return MovieApi.retrofitService.getUpComingMovies().results
    }
    suspend fun searchMovie(query:String):List<Movie>{
        return MovieApi.retrofitService.searchMovie(query=query).results
    }
    suspend fun MovieDetail(id:Int):MovieDetail{
        return MovieApi.retrofitService.MovieDetail(movieId = id)
    }

}