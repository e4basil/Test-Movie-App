package com.baz.diagonalmovieapp.domain.repository

import com.baz.diagonalmovieapp.domain.model.Response
import com.baz.diagonalmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovieListing(id: Int): Resource<Response>

}