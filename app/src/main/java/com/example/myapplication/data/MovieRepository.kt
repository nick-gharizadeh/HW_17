package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetail
import com.example.myapplication.model.VideoMovie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val movieRemoteDataSource: MovieRemoteDataSource,
    val movieLocalDataSource: MovieLocalDataSource
) {
    val allMovies: LiveData<List<Movie?>?>?
    val allUpComingMovies: LiveData<List<Movie?>?>?
    var countMovies: Int

    init {
        allMovies = movieLocalDataSource.allMovies
        allUpComingMovies = movieLocalDataSource.allUpComingMovies
        countMovies = movieLocalDataSource.countMovies
    }

    fun getLocalMovies(): LiveData<List<Movie?>?>? {
        return allMovies
    }

    fun getLocalUpComingMovies(): LiveData<List<Movie?>?>? {
        return allUpComingMovies
    }

    suspend fun insertMovie(movie: Movie) {
        movieLocalDataSource.insertMovie(movie)
    }

    suspend fun getMovie(): List<Movie> {
        return movieRemoteDataSource.getMovie()
    }

    suspend fun getUpComingMovies(): List<Movie> {
        return movieRemoteDataSource.getUpComingMovies()
    }

    suspend fun searchMovie(query: String, adult: Boolean, language: String): List<Movie> {
        return movieRemoteDataSource.searchMovie(query, adult, language)
    }

    suspend fun MovieDetail(id: Int): MovieDetail {
        return movieRemoteDataSource.MovieDetail(id)
    }

    suspend fun videoOfMovie(id: Int): VideoMovie {
        return movieRemoteDataSource.videoOfMovie(id)
    }

}