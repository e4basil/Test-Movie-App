package com.baz.diagonalmovieapp.util

import android.content.Context
import com.baz.diagonalmovieapp.R
import java.io.IOException


class Utils {

    companion object {

        fun getMoviesPoster(key: String?): Int {
            return when (key) {
                "poster1.jpg" -> R.drawable.poster1
                "poster2.jpg" -> R.drawable.poster2
                "poster3.jpg" -> R.drawable.poster3
                "poster4.jpg" -> R.drawable.poster4
                "poster5.jpg" -> R.drawable.poster5
                "poster6.jpg" -> R.drawable.poster6
                "poster7.jpg" -> R.drawable.poster7
                "poster8.jpg" -> R.drawable.poster8
                "poster9.jpg" -> R.drawable.poster9
                else -> R.drawable.placeholder_for_missing_posters
            }
        }


        fun readJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }


         fun handleError(context:Context,error: ErrorType): String {
            return when (error) {
                ErrorType.ContentUnavailable -> context.resources.getString(R.string.no_results_found)
                ErrorType.FileError -> context.resources.getString(R.string.file_error)
                is ErrorType.GenericError -> context.resources.getString(R.string.generic_error)
            }
        }
    }
}