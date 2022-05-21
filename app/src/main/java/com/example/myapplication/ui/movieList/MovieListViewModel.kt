package com.example.myapplication.ui.movieList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domin.Container
import com.example.myapplication.model.Movie
import kotlinx.coroutines.launch


enum class ApiStatus {
    Loading,
    Done,
    Error
}

class MovieListViewModel : ViewModel() {
    val status = MutableLiveData<ApiStatus>()
    val movieList = MutableLiveData<List<Movie>>()
    val movieUpComingList = MutableLiveData<List<Movie>>()

    init {
        getMovie()
        getUpComingMovies()
    }

    fun getMovie() {
        status.value = ApiStatus.Loading
        viewModelScope.launch {
            val list = Container.movieRepository.getMovie()
            movieList.value = list
        }
    }

    fun getUpComingMovies() {
        viewModelScope.launch {
            val list = Container.movieRepository.getUpComingMovies()
            movieUpComingList.value = list
        }
    }

}