package com.baz.diagonalmovieapp.data.repository

import android.content.Context
import com.baz.diagonalmovieapp.data.local.ResponseDto
import com.baz.diagonalmovieapp.domain.model.ContentItem
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

    override fun getDummyData(): Resource<List<ContentItem>> {
        return Resource.Success(
            arrayListOf(
                ContentItem("1 a", "https://randomuser.me/api/portraits/med/men/93.jpg"),
                ContentItem("2 b", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("3 c", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("4 b", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("5 a", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("6 c", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("7 d", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("8 a", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("9 b", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("10 c", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("11 d", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("12 s", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("13 a", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("14 b", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("15 c", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("16 a", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("17 b", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("18 c", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("19 a", "https://randomuser.me/api/portraits/med/women/93.jpg"),
                ContentItem("20 a", "https://randomuser.me/api/portraits/med/women/93.jpg"),

                )
        )
    }


}