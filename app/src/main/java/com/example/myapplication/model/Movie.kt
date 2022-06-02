package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int,
    @ColumnInfo val overview: String,
    @ColumnInfo val poster_path: String?,
    @ColumnInfo val title: String,
    @ColumnInfo val video: Boolean,
    @ColumnInfo var isUpComing: Boolean?,
    @ColumnInfo val backdrop_path: String?,
    @ColumnInfo val vote_average: Double,
    @ColumnInfo val release_date: String
    )
