package com.baz.diagonalmovieapp.data.repository

import android.content.Context
import android.util.Log
import com.baz.diagonalmovieapp.data.local.ResponseDto
import com.baz.diagonalmovieapp.data.mapper.toResponse
import com.baz.diagonalmovieapp.domain.model.Response
import com.baz.diagonalmovieapp.domain.repository.MoviesRepository
import com.baz.diagonalmovieapp.util.Resource
import com.baz.diagonalmovieapp.util.Utils
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(@ApplicationContext val context: Context) :
    MoviesRepository {

    override fun getMovieListing(id: Int): Resource<Response> {
        return try {
            val jsonFileString =
                Utils.readJsonDataFromAsset(context, "CONTENTLISTINGPAGE-PAGE$id.json")
            val data = Gson().fromJson(jsonFileString, ResponseDto::class.java)
//            Log.d("TAG", "getMovieListing: .....  $data")
            Resource.Success(data.toResponse())
        } catch (exception: Exception) {
            Resource.Error("Error")
        }
    }


}