package com.devnight.pruebafirebase.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_discover")
data class Recomendations(
    @ColumnInfo("adult")
    val adult: Boolean,
    @ColumnInfo("backdrop_path")
    val backdropPath: Any,
    @ColumnInfo("genre_ids")
    val genreIds: List<Int>,
    @PrimaryKey
    val id: Int,
    @ColumnInfo("original_language")
    val originalLanguage: String,
    @ColumnInfo("original_title")
    val originalTitle: String,
    @ColumnInfo("overview")
    val overview: String,
    @ColumnInfo("popularity")
    val popularity: Double,
    @ColumnInfo("poster_path")
    val posterPath: Any,
    @ColumnInfo("release_date")
    val releaseDate: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("video")
    val video: Boolean,
    @ColumnInfo("vote_average")
    val voteAverage: Double,
    @ColumnInfo("vote_count")
    val voteCount: Int
)