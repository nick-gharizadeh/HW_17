package com.example.myapplication.model

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity
data class Movie(
    @Json(name = "genre_ids")
    val genre_ids: List<Int>,
    val id: Int,
    val overview: String,
    val poster_path: String?,
    val title: String,
    val video: Boolean,

)
