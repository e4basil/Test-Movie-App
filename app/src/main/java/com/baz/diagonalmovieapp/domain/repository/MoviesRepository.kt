package com.baz.diagonalmovieapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovieListing(): Flow<List<String>>

}