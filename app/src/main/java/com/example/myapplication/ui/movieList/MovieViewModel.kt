package com.example.myapplication.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.model.Movie
import com.example.myapplication.model.VideoMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


enum class ConnectionStatus {
    NotConnected,
    Connected
}

class MovieViewModel(val movieRepository: MovieRepository) : ViewModel() {
    var connectionStatus = MutableLiveData(ConnectionStatus.Connected)
    val movieList = MutableLiveData<List<Movie?>>()
    val searchMovieList = MutableLiveData<List<Movie>>()
    val movieUpComingList = MutableLiveData<List<Movie?>>()
    val movieDetail = MutableLiveData<Movie>()
    val videoOfMovie = MutableLiveData<VideoMovie>()
    val allMovies: LiveData<List<Movie?>?>?
    val allUpComingMovies: LiveData<List<Movie?>?>?
    var countMovies: Int


    init {
        allMovies = movieRepository.getLocalMovies()
        allUpComingMovies = movieRepository.getLocalUpComingMovies()
        countMovies = movieRepository.countMovies
        getMovie()
        getUpComingMovies()
    }

    suspend fun insertMovie(movie: Movie) {
        movieRepository.insertMovie(movie)
    }

    suspend fun getMovieByID(id: Int): Movie {
        return movieRepository.getMovieByID(id)
    }

    fun getMovie() {
        viewModelScope.launch {
            try {
                val list = movieRepository.getMovie()
                movieList.value = list
                connectionStatus.value = ConnectionStatus.Connected
                if (countMovies == 0) {
                    for (movie in list) {
                        viewModelScope.launch {
                            movie.isUpComing = false
                            insertMovie(movie)
                        }
                    }
                }

            } catch (e: SocketTimeoutException) {
                connectionStatus.value = ConnectionStatus.NotConnected
                movieList.value = allMovies?.value
            }
        }
    }

    fun getUpComingMovies() {
        viewModelScope.launch {
            try {
                val list = movieRepository.getUpComingMovies()
                movieUpComingList.value = list
                connectionStatus.value = ConnectionStatus.Connected
                if (countMovies <= 20) {
                    for (movie in list) {
                        viewModelScope.launch {
                            movie.isUpComing = true
                            insertMovie(movie)
                        }
                    }
                }
            } catch (e: SocketTimeoutException) {
                connectionStatus.value = ConnectionStatus.NotConnected
                movieUpComingList.value = allUpComingMovies?.value
            }
        }
    }

    fun getSearchMovies(query: String, adult: Boolean, language: String) {
        viewModelScope.launch {
          if (connectionStatus.value == ConnectionStatus.Connected) {
              val list = movieRepository.searchMovie(query, adult, language)
              searchMovieList.value = list
              connectionStatus.value = ConnectionStatus.Connected
          }
            else
          {
              searchMovieList.value=movieRepository.searchLocalMovie("%$query%")
          }
        }
    }

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
                if (connectionStatus.value == ConnectionStatus.Connected) {
                    movieDetail.value = movieRepository.MovieDetail(id)
                } else {
                    movieDetail.value = getMovieByID(id)!!

                }
        }
}


fun getVideoOfMovie(id: Int) {
    viewModelScope.launch {
        try {
            videoOfMovie.value = movieRepository.videoOfMovie(id)
            connectionStatus.value = ConnectionStatus.Connected

        } catch (e: SocketTimeoutException) {
            connectionStatus.value = ConnectionStatus.NotConnected
        }
    }
}


}