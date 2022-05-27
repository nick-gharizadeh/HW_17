package com.example.myapplication.ui.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domin.Container
import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieDetail
import com.example.myapplication.model.VideoMovie
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


enum class ApiStatus {
    Loading,
    Done,
    Error
}

class MovieRemoteViewModel : ViewModel() {
    var connectionStatus = MutableLiveData(false)
    val movieList = MutableLiveData<List<Movie>>()
    val searchMovieList = MutableLiveData<List<Movie>>()
    val movieUpComingList = MutableLiveData<List<Movie>>()
    val movieDetail = MutableLiveData<MovieDetail>()
    val videoOfMovie = MutableLiveData<VideoMovie>()

    init {
        getMovie()
        getUpComingMovies()
    }

    fun getMovie() {
        viewModelScope.launch {
            try {
                val list = Container.movieRepository.getMovie()
                movieList.value = list
                connectionStatus.value = false
            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true
            }
        }
    }

    fun getUpComingMovies() {
        viewModelScope.launch {
            try {
                val list = Container.movieRepository.getUpComingMovies()
                movieUpComingList.value = list
                connectionStatus.value = false

            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true
            }
        }
    }

    fun getSearchMovies(query: String, adult: Boolean, language: String) {
        viewModelScope.launch {
            try {
                val list = Container.movieRepository.searchMovie(query, adult, language)
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
                movieDetail.value = Container.movieRepository.MovieDetail(id)
                connectionStatus.value = false
            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true

            }
        }
    }


    fun getVideoOfMovie(id: Int) {
        viewModelScope.launch {
            try {
                videoOfMovie.value = Container.movieRepository.videoOfMovie(id)
                connectionStatus.value = false

            } catch (e: SocketTimeoutException) {
                connectionStatus.value = true
            }
        }
    }


}