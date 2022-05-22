package com.example.myapplication.data

import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetail

class MovieRepository (val movieRemoteDataSource:MovieRemoteDataSource){
    
    suspend fun getMovie():List<Movie>{
        return movieRemoteDataSource.getMovie()
    }
    suspend fun getUpComingMovies():List<Movie>{
        return movieRemoteDataSource.getUpComingMovies()
    }
    suspend fun searchMovie(query:String):List<Movie>{
        return movieRemoteDataSource.searchMovie(query)
    }
    suspend fun MovieDetail(id:Int): MovieDetail {
        return movieRemoteDataSource.MovieDetail(id)
    }



}