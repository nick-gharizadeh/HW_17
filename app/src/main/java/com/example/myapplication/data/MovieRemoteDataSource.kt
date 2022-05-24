package com.example.myapplication.data

import com.example.myapplication.data.network.MovieApi
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetail
import com.example.myapplication.model.VideoMovie

class MovieRemoteDataSource {

    suspend fun getMovie():List<Movie>{
        return MovieApi.retrofitService.getMovies().results
    }
    suspend fun getUpComingMovies():List<Movie>{
        return MovieApi.retrofitService.getUpComingMovies().results
    }
    suspend fun searchMovie(query:String,adult:Boolean):List<Movie>{
        return MovieApi.retrofitService.searchMovie(query=query, include_adult = adult).results
    }
    suspend fun MovieDetail(id:Int):MovieDetail{
        return MovieApi.retrofitService.MovieDetail(movieId = id)
    }
    suspend fun videoOfMovie(id:Int):VideoMovie{
        return MovieApi.retrofitService.videoOfMovie(movieId = id)
    }
}