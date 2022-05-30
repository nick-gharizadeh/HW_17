package com.example.myapplication.data

import com.example.myapplication.data.network.ApiService
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetail
import com.example.myapplication.model.VideoMovie

class MovieRemoteDataSource(val movieApiService : ApiService) {

    suspend fun getMovie():List<Movie>{
        return movieApiService.getMovies().results
    }
    suspend fun getUpComingMovies():List<Movie>{
        return movieApiService.getUpComingMovies().results
    }
    suspend fun searchMovie(query:String,adult:Boolean,language: String):List<Movie>{
        return movieApiService.searchMovie(query=query, include_adult = adult, language = language).results
    }
    suspend fun MovieDetail(id:Int):MovieDetail{
        return movieApiService.MovieDetail(movieId = id)
    }
    suspend fun videoOfMovie(id:Int):VideoMovie{
        return movieApiService.videoOfMovie(movieId = id)
    }
}