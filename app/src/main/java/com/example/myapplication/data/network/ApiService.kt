package com.example.myapplication.data.network

import com.example.myapplication.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val api_key = "8615e332ad100989dfaaba4d95fa88c7"


interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("page")page :Int = 1,
        @Query("api_key") apiKey :String = api_key
    ): MovieListApiResult

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("page")page :Int =2,
        @Query("api_key") apiKey :String = api_key
    ): MovieListApiResult

    @GET("search/movie")
    suspend fun searchMovie(
//        @Query("page")page :Int,
        @Query("api_key") apiKey :String = api_key,
        @Query("query") query: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("language") language: String
    ):SearchMovie

    @GET("movie/{movie_id}")
    suspend fun MovieDetail(
        @Path(value = "movie_id") movieId: Int,
        @Query("api_key") apiKey :String = api_key
    ): Movie

    @GET("movie/{movie_id}/videos")
    suspend fun videoOfMovie(
        @Path(value = "movie_id") movieId: Int,
        @Query("api_key") apiKey :String = api_key
    ): VideoMovie

}



