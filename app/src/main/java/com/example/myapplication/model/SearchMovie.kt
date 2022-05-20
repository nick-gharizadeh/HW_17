package com.example.myapplication.model

data class SearchMovie(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)