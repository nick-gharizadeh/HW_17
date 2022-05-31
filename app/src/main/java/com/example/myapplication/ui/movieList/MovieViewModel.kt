package com.example.myapplication.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetail
import com.example.myapplication.model.VideoMovie
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


enum class ConnectionStatus {
    NotConnected,
    Connected
}

class MovieViewModel(val movieRepository: MovieRepository) : ViewModel() {
    var connectionStatus = MutableLiveData(false)
    val movieList = MutableLiveData<List<Movie>>()
    val searchMovieList = MutableLiveData<List<Movie>>()
    val movieUpComingList = MutableLiveData<List<Movie>>()
    val movieDetail = MutableLiveData<MovieDetail>()
    val videoOfMovie = MutableLiveData<VideoMovie>()
    val allMovies: LiveData<List<Movie?>?>?
    var countMovies: Int

    init {
        allMovies = movieRepository.allMovies
        countMovies = movieRepository.countMovies
    }

    fun insertMovie(movie: Movie) {
        movieRepository.insertMovie(movie)
    }

    init {
        getMovie()
        getUpComingMovies()
    }

    fun getMovie() {
        viewModelScope.launch {
            try {
                val list = movieRepository.getMovie()
                movieList.value = list
                connectionStatus.value = false
                if (countMovies == 0) {
                    for (movie in list) {
                        movie.isUpComing = false
                        insertMovie(movie)
                    }
                }
            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true
            }
        }
    }

    fun getUpComingMovies() {
        viewModelScope.launch {
            try {
                val list = movieRepository.getUpComingMovies()
                movieUpComingList.value = list
                connectionStatus.value = false
                if (countMovies == 0) {
                    for (movie in list) {
                        movie.isUpComing = true
                        insertMovie(movie)
                    }

                }

            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true
            }
        }
    }

    fun getSearchMovies(query: String, adult: Boolean, language: String) {
        viewModelScope.launch {
            try {
                val list = movieRepository.searchMovie(query, adult, language)
                searchMovieList.value = list
                connectionStatus.value = false

            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true
            }
        }
    }

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            try {
                movieDetail.value = movieRepository.MovieDetail(id)
                connectionStatus.value = false
            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true

            }
        }
    }


    fun getVideoOfMovie(id: Int) {
        viewModelScope.launch {
            try {
                videoOfMovie.value = movieRepository.videoOfMovie(id)
                connectionStatus.value = false

            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true
            }
        }
    }


}