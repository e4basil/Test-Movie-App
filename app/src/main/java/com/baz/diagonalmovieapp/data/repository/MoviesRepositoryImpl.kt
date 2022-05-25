package com.baz.diagonalmovieapp.data.repository

import com.baz.diagonalmovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl:MoviesRepository {


    override fun getMovieListing(): Flow<List<String>> {
        TODO("Not yet implemented")
    }


}